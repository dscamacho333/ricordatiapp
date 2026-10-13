import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { CheckSquare2, Eye, EyeOff } from 'lucide-react';
import { login } from '../api/authService';
import { getApiError } from '../api/http';
import { useAuth } from '../context/AuthContext';

export default function LoginPage() {
  const navigate = useNavigate();
  const { signIn, refreshUser } = useAuth();
  const [form, setForm] = useState({ email: '', password: '' });
  const [showPassword, setShowPassword] = useState(false);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');

  const submit = async (event) => {
    event.preventDefault();
    setLoading(true);
    setError('');

    try {
      const response = await login(form);
      signIn(response);
      await refreshUser();
      navigate('/tasks');
    } catch (err) {
      setError(getApiError(err, 'Correo o contraseña incorrectos.'));
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="auth-page">
      <section className="auth-panel auth-brand-panel">
        <div className="auth-brand">
          <div className="brand-mark large"><CheckSquare2 size={28} /></div>
          <strong>Ricordati</strong>
        </div>
        <div className="auth-copy">
          <span className="eyebrow">Organiza tu día</span>
          <h1>Tus tareas, prioridades y recordatorios en un solo lugar.</h1>
          <p>Una interfaz simple para mantener el control de lo que importa y recibir tus recordatorios a tiempo.</p>
        </div>
      </section>

      <section className="auth-panel auth-form-panel">
        <form className="auth-card" onSubmit={submit}>
          <div>
            <span className="eyebrow">Bienvenido</span>
            <h2>Inicia sesión</h2>
            <p>Ingresa con la cuenta registrada en Ricordati.</p>
          </div>

          {error && <div className="alert error">{error}</div>}

          <label className="field">
            <span>Correo electrónico</span>
            <input type="email" value={form.email} onChange={(e) => setForm({ ...form, email: e.target.value })} required placeholder="correo@ejemplo.com" />
          </label>

          <label className="field">
            <span>Contraseña</span>
            <div className="password-input">
              <input type={showPassword ? 'text' : 'password'} value={form.password} onChange={(e) => setForm({ ...form, password: e.target.value })} required placeholder="Tu contraseña" />
              <button type="button" onClick={() => setShowPassword((value) => !value)}>
                {showPassword ? <EyeOff size={18} /> : <Eye size={18} />}
              </button>
            </div>
          </label>

          <button className="button primary wide" disabled={loading}>{loading ? 'Ingresando...' : 'Ingresar'}</button>
          <p className="auth-link">¿No tienes cuenta? <Link to="/register">Crear cuenta</Link></p>
        </form>
      </section>
    </div>
  );
}
