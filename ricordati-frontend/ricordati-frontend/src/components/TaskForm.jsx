import { useEffect, useMemo, useState } from 'react';

function toLocalDateTime(value) {
  if (!value) return '';
  const date = new Date(value);
  const offset = date.getTimezoneOffset();
  const local = new Date(date.getTime() - offset * 60000);
  return local.toISOString().slice(0, 16);
}

const emptyForm = {
  title: '',
  description: '',
  taskCategoryId: '',
  dueDate: '',
  completionDate: '',
  taskStatusId: '',
  taskPriorityLevelId: '',
  taskReminderTimeId: '',
};

export default function TaskForm({ initialTask, categories, statuses, priorities, reminders, onSubmit, onCancel, saving }) {
  const [form, setForm] = useState(emptyForm);

  const initialValues = useMemo(() => {
    if (!initialTask) return emptyForm;
    return {
      title: initialTask.title || '',
      description: initialTask.description || '',
      taskCategoryId: initialTask.taskCategory?.id || '',
      dueDate: toLocalDateTime(initialTask.dueDate),
      completionDate: toLocalDateTime(initialTask.completionDate),
      taskStatusId: initialTask.taskStatus?.id || '',
      taskPriorityLevelId: initialTask.taskPriorityLevel?.id || '',
      taskReminderTimeId: initialTask.taskReminderTime?.id || '',
    };
  }, [initialTask]);

  useEffect(() => setForm(initialValues), [initialValues]);

  const change = (event) => {
    const { name, value } = event.target;
    setForm((current) => ({ ...current, [name]: value }));
  };

  const submit = (event) => {
    event.preventDefault();

    const payload = {
      taskCategoryId: Number(form.taskCategoryId),
      title: form.title.trim(),
      description: form.description.trim(),
      dueDate: new Date(form.dueDate).toISOString(),
      taskStatusId: Number(form.taskStatusId),
      taskPriorityLevelId: Number(form.taskPriorityLevelId),
      taskReminderTimeId: Number(form.taskReminderTimeId),
    };

    if (initialTask) {
      payload.completionDate = form.completionDate
        ? new Date(form.completionDate).toISOString()
        : null;
    }

    onSubmit(payload);
  };

  return (
    <form onSubmit={submit} className="form-grid">
      <label className="field field-span-2">
        <span>Título</span>
        <input name="title" value={form.title} onChange={change} maxLength={25} required placeholder="Ej. Entregar informe" />
        <small>{form.title.length}/25</small>
      </label>

      <label className="field field-span-2">
        <span>Descripción</span>
        <textarea name="description" value={form.description} onChange={change} maxLength={150} rows={4} placeholder="Describe la tarea..." />
        <small>{form.description.length}/150</small>
      </label>

      <label className="field">
        <span>Categoría</span>
        <select name="taskCategoryId" value={form.taskCategoryId} onChange={change} required>
          <option value="">Selecciona</option>
          {categories.map((item) => <option key={item.id} value={item.id}>{item.name}</option>)}
        </select>
      </label>

      <label className="field">
        <span>Vencimiento</span>
        <input type="datetime-local" name="dueDate" value={form.dueDate} onChange={change} required />
      </label>

      <label className="field">
        <span>Estado</span>
        <select name="taskStatusId" value={form.taskStatusId} onChange={change} required>
          <option value="">Selecciona</option>
          {statuses.map((item) => <option key={item.id} value={item.id}>{item.name}</option>)}
        </select>
      </label>

      <label className="field">
        <span>Prioridad</span>
        <select name="taskPriorityLevelId" value={form.taskPriorityLevelId} onChange={change} required>
          <option value="">Selecciona</option>
          {priorities.map((item) => <option key={item.id} value={item.id}>{item.name}</option>)}
        </select>
      </label>

      <label className="field">
        <span>Recordatorio</span>
        <select name="taskReminderTimeId" value={form.taskReminderTimeId} onChange={change} required>
          <option value="">Selecciona</option>
          {reminders.map((item) => (
            <option key={item.id} value={item.id}>
              {item.time} {String(item.timeUnit || '').toLowerCase()}
            </option>
          ))}
        </select>
      </label>

      {initialTask && (
        <label className="field">
          <span>Fecha de finalización</span>
          <input type="datetime-local" name="completionDate" value={form.completionDate} onChange={change} />
        </label>
      )}

      <div className="form-actions field-span-2">
        <button type="button" className="button secondary" onClick={onCancel}>Cancelar</button>
        <button type="submit" className="button primary" disabled={saving}>
          {saving ? 'Guardando...' : initialTask ? 'Guardar cambios' : 'Crear tarea'}
        </button>
      </div>
    </form>
  );
}
