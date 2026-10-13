import { Navigate, Outlet } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

export default function ProtectedRoute() {
  const { loading } = useAuth();
  const token = localStorage.getItem('token');

  if (loading) {
    return <div className="page-center"><div className="loader" /></div>;
  }

  return token ? <Outlet /> : <Navigate to="/login" replace />;
}
