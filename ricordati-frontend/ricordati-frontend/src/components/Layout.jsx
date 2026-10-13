import { NavLink, Outlet, useNavigate } from 'react-router-dom';
import { CalendarCheck2, FolderKanban, LogOut, UserRound, CheckSquare2 } from 'lucide-react';
import { useAuth } from '../context/AuthContext';

export default function Layout() {
  const navigate = useNavigate();
  const { user, signOut } = useAuth();

  const logout = () => {
    signOut();
    navigate('/login');
  };

  const displayName = [user?.firstName, user?.lastName].filter(Boolean).join(' ') || 'Usuario';

  return (
    <div className="app-shell">
      <aside className="sidebar">
        <div className="brand">
          <div className="brand-mark"><CheckSquare2 size={22} /></div>
          <div>
            <strong>Ricordati</strong>
            <span>Task manager</span>
          </div>
        </div>

        <nav className="sidebar-nav">
          <NavLink to="/tasks" className={({ isActive }) => isActive ? 'nav-item active' : 'nav-item'}>
            <CalendarCheck2 size={18} />
            Tareas
          </NavLink>
          <NavLink to="/categories" className={({ isActive }) => isActive ? 'nav-item active' : 'nav-item'}>
            <FolderKanban size={18} />
            Categorías
          </NavLink>
          <NavLink to="/profile" className={({ isActive }) => isActive ? 'nav-item active' : 'nav-item'}>
            <UserRound size={18} />
            Perfil
          </NavLink>
        </nav>

        <div className="sidebar-footer">
          <div className="sidebar-user">
            <div className="avatar">{displayName.charAt(0).toUpperCase()}</div>
            <div>
              <strong>{displayName}</strong>
              <span>{user?.account?.email || ''}</span>
            </div>
          </div>
          <button className="icon-button" onClick={logout} title="Cerrar sesión">
            <LogOut size={18} />
          </button>
        </div>
      </aside>

      <main className="main-content">
        <Outlet />
      </main>
    </div>
  );
}
