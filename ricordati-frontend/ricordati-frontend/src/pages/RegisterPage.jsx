import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { ArrowLeft, CheckSquare2 } from 'lucide-react';
import { createUser } from '../api/userService';
import { getApiError } from '../api/http';

const initialForm = {
  firstName: '', middleName: '', lastName: '', secondLastName: '', age: '', governmentId: '', email: '', password: '',
};

export default function RegisterPage() {
  const navigate = useNavigate();
  const [form, setForm] = useState(initialForm);
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);

  const change = (event) => setForm((current) => ({ ...current, [event.target.name]: event.target.value }));

  const submit = async (event) => {
    event.preventDefault();
    setError('');

    if (!/^\d{10}$/.test(form.governmentId)) {
      setError('El documento debe tener exactamente 10 números.');
      return;
    }

    setLoading(true);
    try {
      await createUser({
        firstName: form.firstName.trim(),
        middleName: form.middleName.trim(),
        lastName: form.lastName.trim(),
        secondLastName: form.secondLastName.trim(),
        age: Number(form.age),
        governmentId: form.governmentId,
        account: { email: form.email.trim(), password: form.password },
      });
      navigate('/login', { state: { registered: true } });
    } catch (err) {
      setError(getApiError(err, 'No fue posible crear la cuenta.'));
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="register-page">
      <div className="register-header">
        <div className="auth-brand dark"><div className="brand-mark"><CheckSquare2 size={22} /></div><strong>Ricordati</strong></div>
        <Link to="/login" className="back-link"><ArrowLeft size={17} /> Volver al inicio</Link>
      </div>

      <form className="register-card" onSubmit={submit}>
        <div className="page-title compact">
          <span className="eyebrow">Nueva cuenta</span>
          <h1>Crea tu perfil</h1>
          <p>Completa tus datos para comenzar a gestionar tareas y recordatorios.</p>
        </div>

        {error && <div className="alert error field-span-2">{error}</div>}

        <label className="field"><span>Primer nombre</span><input name="firstName" maxLength={25} value={form.firstName} onChange={change} required /></label>
        <label className="field"><span>Segundo nombre</span><input name="middleName" maxLength={25} value={form.middleName} onChange={change} /></label>
        <label className="field"><span>Primer apellido</span><input name="lastName" maxLength={25} value={form.lastName} onChange={change} required /></label>
        <label className="field"><span>Segundo apellido</span><input name="secondLastName" maxLength={25} value={form.secondLastName} onChange={change} /></label>
        <label className="field"><span>Edad</span><input type="number" min="18" name="age" value={form.age} onChange={change} required /></label>
        <label className="field"><span>Documento</span><input name="governmentId" inputMode="numeric" maxLength={10} value={form.governmentId} onChange={change} required placeholder="10 dígitos" /></label>
        <label className="field"><span>Correo</span><input type="email" name="email" maxLength={50} value={form.email} onChange={change} required /></label>
        <label className="field"><span>Contraseña</span><input type="password" name="password" maxLength={15} value={form.password} onChange={change} required /></label>

        <div className="form-actions field-span-2">
          <button className="button primary" disabled={loading}>{loading ? 'Creando...' : 'Crear cuenta'}</button>
        </div>
      </form>
    </div>
  );
}
