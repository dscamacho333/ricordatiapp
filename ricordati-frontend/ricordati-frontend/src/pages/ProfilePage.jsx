import { useEffect, useState } from 'react';
import { Calendar, Mail, Save, ShieldCheck, UserRound } from 'lucide-react';
import { updateUser } from '../api/userService';
import { getApiError } from '../api/http';
import { useAuth } from '../context/AuthContext';

export default function ProfilePage() {
  const { user, refreshUser } = useAuth();
  const [form, setForm] = useState({ firstName: '', middleName: '', lastName: '', secondLastName: '', email: '', password: '' });
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');
  const [saving, setSaving] = useState(false);

  useEffect(() => {
    if (!user) return;
    setForm({
      firstName: user.firstName || '', middleName: user.middleName || '', lastName: user.lastName || '', secondLastName: user.secondLastName || '',
      email: user.account?.email || '', password: '',
    });
  }, [user]);

  const change = (event) => setForm((current) => ({ ...current, [event.target.name]: event.target.value }));

  const submit = async (event) => {
    event.preventDefault();
    setSaving(true); setError(''); setMessage('');
    try {
      await updateUser({
        firstName: form.firstName.trim(), middleName: form.middleName.trim(), lastName: form.lastName.trim(), secondLastName: form.secondLastName.trim(),
        account: { email: form.email.trim(), password: form.password },
      });
      await refreshUser();
      setForm((current) => ({ ...current, password: '' }));
      setMessage('Perfil actualizado correctamente.');
    } catch (err) { setError(getApiError(err, 'No fue posible actualizar el perfil.')); }
    finally { setSaving(false); }
  };

  return (
    <div>
      <div className="page-title"><span className="eyebrow">Tu cuenta</span><h1>Perfil</h1><p>Consulta tu información y actualiza los datos permitidos por la API.</p></div>

      <div className="profile-layout">
        <aside className="profile-summary content-card">
          <div className="profile-avatar"><UserRound size={30} /></div>
          <h2>{[user?.firstName, user?.lastName].filter(Boolean).join(' ')}</h2>
          <p>{user?.governmentId}</p>
          <div className="profile-info"><Mail size={17} /><span>{user?.account?.email}</span></div>
          <div className="profile-info"><ShieldCheck size={17} /><span>{user?.account?.accountStatus?.name}</span></div>
          <div className="profile-info"><Calendar size={17} /><span>Cuenta desde {user?.account?.createdAt || '-'}</span></div>
          <div className="profile-readonly"><span>Edad</span><strong>{user?.age ?? '-'}</strong></div>
        </aside>

        <section className="content-card profile-form-card">
          <h2>Editar información</h2>
          <p className="section-description">Edad y documento no aparecen en el DTO de actualización, por lo que se muestran como información de solo lectura.</p>
          {message && <div className="alert success">{message}</div>}
          {error && <div className="alert error">{error}</div>}
          <form className="form-grid" onSubmit={submit}>
            <label className="field"><span>Primer nombre</span><input name="firstName" value={form.firstName} onChange={change} maxLength={25} /></label>
            <label className="field"><span>Segundo nombre</span><input name="middleName" value={form.middleName} onChange={change} maxLength={25} /></label>
            <label className="field"><span>Primer apellido</span><input name="lastName" value={form.lastName} onChange={change} maxLength={25} /></label>
            <label className="field"><span>Segundo apellido</span><input name="secondLastName" value={form.secondLastName} onChange={change} maxLength={25} /></label>
            <label className="field"><span>Correo</span><input type="email" name="email" value={form.email} onChange={change} maxLength={50} /></label>
            <label className="field"><span>Nueva contraseña</span><input type="password" name="password" value={form.password} onChange={change} maxLength={15} placeholder="Déjala vacía si no cambia" /></label>
            <div className="form-actions field-span-2"><button className="button primary" disabled={saving}><Save size={17} /> {saving ? 'Guardando...' : 'Guardar cambios'}</button></div>
          </form>
        </section>
      </div>
    </div>
  );
}
