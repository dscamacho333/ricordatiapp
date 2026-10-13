import { useEffect, useState } from 'react';
import { Edit3, FolderKanban, Plus, Trash2 } from 'lucide-react';
import { createCategory, deleteCategory, getCategories, updateCategory } from '../api/catalogService';
import { getApiError } from '../api/http';
import Modal from '../components/Modal';

export default function CategoriesPage() {
  const [categories, setCategories] = useState([]);
  const [loading, setLoading] = useState(true);
  const [modalOpen, setModalOpen] = useState(false);
  const [editing, setEditing] = useState(null);
  const [form, setForm] = useState({ name: '', description: '' });
  const [error, setError] = useState('');
  const [saving, setSaving] = useState(false);

  const load = async () => {
    setLoading(true);
    try { setCategories(await getCategories()); }
    catch (err) { setError(getApiError(err, 'No fue posible cargar las categorías.')); }
    finally { setLoading(false); }
  };

  useEffect(() => { load(); }, []);

  const openCreate = () => { setEditing(null); setForm({ name: '', description: '' }); setModalOpen(true); };
  const openEdit = (category) => { setEditing(category); setForm({ name: category.name, description: category.description || '' }); setModalOpen(true); };

  const submit = async (event) => {
    event.preventDefault();
    setSaving(true);
    try {
      if (editing) await updateCategory(editing.id, form);
      else await createCategory(form);
      setModalOpen(false);
      await load();
    } catch (err) {
      setError(getApiError(err, 'No fue posible guardar la categoría.'));
    } finally { setSaving(false); }
  };

  const remove = async (category) => {
    if (!window.confirm(`¿Eliminar la categoría “${category.name}”?`)) return;
    try {
      await deleteCategory(category.id);
      setCategories((current) => current.filter((item) => item.id !== category.id));
    } catch (err) { setError(getApiError(err, 'No fue posible eliminar la categoría.')); }
  };

  return (
    <div>
      <div className="page-heading-row">
        <div className="page-title"><span className="eyebrow">Organización</span><h1>Categorías</h1><p>Agrupa tus tareas según su contexto o propósito.</p></div>
        <button className="button primary" onClick={openCreate}><Plus size={18} /> Nueva categoría</button>
      </div>

      {error && <div className="alert error">{error}</div>}

      {loading ? <div className="empty-state"><div className="loader" /></div> : (
        <div className="category-grid">
          {categories.map((category) => (
            <article className="category-card" key={category.id}>
              <span className="category-icon"><FolderKanban size={21} /></span>
              <div className="category-copy"><h3>{category.name}</h3><p>{category.description || 'Sin descripción.'}</p></div>
              <div className="category-actions">
                <button className="icon-button" onClick={() => openEdit(category)}><Edit3 size={17} /></button>
                <button className="icon-button danger-text" onClick={() => remove(category)}><Trash2 size={17} /></button>
              </div>
            </article>
          ))}
          {!categories.length && <div className="empty-state"><FolderKanban size={38} /><h3>Aún no tienes categorías</h3><p>Crea la primera para organizar tus tareas.</p></div>}
        </div>
      )}

      <Modal open={modalOpen} title={editing ? 'Editar categoría' : 'Nueva categoría'} onClose={() => setModalOpen(false)} width="520px">
        <form className="form-grid" onSubmit={submit}>
          <label className="field field-span-2"><span>Nombre</span><input value={form.name} onChange={(e) => setForm({ ...form, name: e.target.value })} maxLength={25} required /></label>
          <label className="field field-span-2"><span>Descripción</span><textarea value={form.description} onChange={(e) => setForm({ ...form, description: e.target.value })} maxLength={150} rows={4} /></label>
          <div className="form-actions field-span-2"><button type="button" className="button secondary" onClick={() => setModalOpen(false)}>Cancelar</button><button className="button primary" disabled={saving}>{saving ? 'Guardando...' : 'Guardar'}</button></div>
        </form>
      </Modal>
    </div>
  );
}
