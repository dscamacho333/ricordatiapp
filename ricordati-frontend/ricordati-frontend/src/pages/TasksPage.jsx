import { useCallback, useEffect, useMemo, useState } from 'react';
import { CalendarDays, CheckCircle2, Clock3, Edit3, Plus, Search, Trash2 } from 'lucide-react';
import { createTask, deleteTask, getTasks, updateTask } from '../api/taskService';
import { getCategories, getPriorityLevels, getReminderTimes, getTaskStatuses } from '../api/catalogService';
import { getApiError } from '../api/http';
import Modal from '../components/Modal';
import TaskForm from '../components/TaskForm';

function formatDate(value) {
  if (!value) return 'Sin fecha';
  return new Intl.DateTimeFormat('es-CO', { dateStyle: 'medium', timeStyle: 'short' }).format(new Date(value));
}

function statusClass(name = '') {
  const normalized = name.toLowerCase();
  if (normalized.includes('complet') || normalized.includes('termin')) return 'success';
  if (normalized.includes('progreso') || normalized.includes('curso')) return 'info';
  if (normalized.includes('cancel')) return 'danger';
  return 'neutral';
}

function priorityClass(name = '') {
  const normalized = name.toLowerCase();
  if (normalized.includes('alta') || normalized.includes('high')) return 'danger';
  if (normalized.includes('media') || normalized.includes('medium')) return 'warning';
  return 'success';
}

export default function TasksPage() {
  const [tasks, setTasks] = useState([]);
  const [catalogs, setCatalogs] = useState({ categories: [], statuses: [], priorities: [], reminders: [] });
  const [loading, setLoading] = useState(true);
  const [saving, setSaving] = useState(false);
  const [error, setError] = useState('');
  const [search, setSearch] = useState('');
  const [statusFilter, setStatusFilter] = useState('');
  const [modalOpen, setModalOpen] = useState(false);
  const [editingTask, setEditingTask] = useState(null);

  const load = useCallback(async () => {
    setLoading(true);
    setError('');
    try {
      const [taskData, categories, statuses, priorities, reminders] = await Promise.all([
        getTasks(), getCategories(), getTaskStatuses(), getPriorityLevels(), getReminderTimes(),
      ]);
      setTasks(taskData);
      setCatalogs({ categories, statuses, priorities, reminders });
    } catch (err) {
      setError(getApiError(err, 'No fue posible cargar las tareas.'));
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => { load(); }, [load]);

  const filteredTasks = useMemo(() => tasks.filter((task) => {
    const haystack = `${task.title} ${task.description || ''} ${task.taskCategory?.name || ''}`.toLowerCase();
    const matchesSearch = haystack.includes(search.toLowerCase());
    const matchesStatus = !statusFilter || String(task.taskStatus?.id) === statusFilter;
    return matchesSearch && matchesStatus;
  }), [tasks, search, statusFilter]);

  const completed = tasks.filter((task) => statusClass(task.taskStatus?.name) === 'success').length;
  const upcoming = tasks.filter((task) => task.dueDate && new Date(task.dueDate) >= new Date()).length;

  const openCreate = () => { setEditingTask(null); setModalOpen(true); };
  const openEdit = (task) => { setEditingTask(task); setModalOpen(true); };

  const saveTask = async (payload) => {
    setSaving(true);
    setError('');
    try {
      if (editingTask) await updateTask(editingTask.id, payload);
      else await createTask(payload);
      setModalOpen(false);
      setEditingTask(null);
      await load();
    } catch (err) {
      setError(getApiError(err, 'No fue posible guardar la tarea.'));
    } finally {
      setSaving(false);
    }
  };

  const remove = async (task) => {
    if (!window.confirm(`¿Eliminar la tarea “${task.title}”?`)) return;
    try {
      await deleteTask(task.id);
      setTasks((current) => current.filter((item) => item.id !== task.id));
    } catch (err) {
      setError(getApiError(err, 'No fue posible eliminar la tarea.'));
    }
  };

  return (
    <div>
      <div className="page-heading-row">
        <div className="page-title">
          <span className="eyebrow">Panel de trabajo</span>
          <h1>Mis tareas</h1>
          <p>Consulta, crea y organiza tus pendientes.</p>
        </div>
        <button className="button primary" onClick={openCreate}><Plus size={18} /> Nueva tarea</button>
      </div>

      <div className="stat-grid">
        <article className="stat-card"><span className="stat-icon"><CalendarDays size={20} /></span><div><strong>{tasks.length}</strong><span>Tareas totales</span></div></article>
        <article className="stat-card"><span className="stat-icon"><Clock3 size={20} /></span><div><strong>{upcoming}</strong><span>Por vencer</span></div></article>
        <article className="stat-card"><span className="stat-icon"><CheckCircle2 size={20} /></span><div><strong>{completed}</strong><span>Completadas</span></div></article>
      </div>

      {error && <div className="alert error">{error}</div>}

      <section className="content-card">
        <div className="toolbar">
          <div className="search-box"><Search size={18} /><input value={search} onChange={(e) => setSearch(e.target.value)} placeholder="Buscar tarea..." /></div>
          <select className="toolbar-select" value={statusFilter} onChange={(e) => setStatusFilter(e.target.value)}>
            <option value="">Todos los estados</option>
            {catalogs.statuses.map((item) => <option key={item.id} value={item.id}>{item.name}</option>)}
          </select>
        </div>

        {loading ? (
          <div className="empty-state"><div className="loader" /><p>Cargando tareas...</p></div>
        ) : filteredTasks.length === 0 ? (
          <div className="empty-state"><CalendarDays size={38} /><h3>No hay tareas para mostrar</h3><p>Crea una nueva o cambia los filtros de búsqueda.</p></div>
        ) : (
          <div className="task-list">
            {filteredTasks.map((task) => (
              <article className="task-card" key={task.id}>
                <div className="task-main">
                  <div className="task-topline">
                    <span className={`badge ${priorityClass(task.taskPriorityLevel?.name)}`}>{task.taskPriorityLevel?.name}</span>
                    <span className={`badge ${statusClass(task.taskStatus?.name)}`}>{task.taskStatus?.name}</span>
                  </div>
                  <h3>{task.title}</h3>
                  <p>{task.description || 'Sin descripción.'}</p>
                  <div className="task-meta">
                    <span>{task.taskCategory?.name || 'Sin categoría'}</span>
                    <span>Vence: {formatDate(task.dueDate)}</span>
                    <span>Recordar: {task.taskReminderTime?.time} {String(task.taskReminderTime?.timeUnit || '').toLowerCase()}</span>
                  </div>
                </div>
                <div className="task-actions">
                  <button className="icon-button" onClick={() => openEdit(task)} title="Editar"><Edit3 size={18} /></button>
                  <button className="icon-button danger-text" onClick={() => remove(task)} title="Eliminar"><Trash2 size={18} /></button>
                </div>
              </article>
            ))}
          </div>
        )}
      </section>

      <Modal open={modalOpen} title={editingTask ? 'Editar tarea' : 'Nueva tarea'} onClose={() => setModalOpen(false)}>
        <TaskForm
          initialTask={editingTask}
          categories={catalogs.categories}
          statuses={catalogs.statuses}
          priorities={catalogs.priorities}
          reminders={catalogs.reminders}
          onSubmit={saveTask}
          onCancel={() => setModalOpen(false)}
          saving={saving}
        />
      </Modal>
    </div>
  );
}
