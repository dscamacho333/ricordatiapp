import { createContext, useContext, useEffect, useMemo, useState } from 'react';
import { getCurrentUser } from '../api/userService';

const AuthContext = createContext(null);

export function AuthProvider({ children }) {
  const [user, setUser] = useState(() => {
    const stored = localStorage.getItem('user');
    return stored ? JSON.parse(stored) : null;
  });
  const [loading, setLoading] = useState(Boolean(localStorage.getItem('token')));

  useEffect(() => {
    if (!localStorage.getItem('token')) {
      setLoading(false);
      return;
    }

    getCurrentUser()
      .then((data) => {
        setUser(data);
        localStorage.setItem('user', JSON.stringify(data));
      })
      .catch(() => {
        setUser(null);
      })
      .finally(() => setLoading(false));
  }, []);

  const signIn = (loginResponse) => {
    const tokenType = loginResponse.tokeType || loginResponse.tokenType || 'Bearer';
    localStorage.setItem('token', loginResponse.token);
    localStorage.setItem('tokenType', tokenType);
  };

  const refreshUser = async () => {
    const data = await getCurrentUser();
    setUser(data);
    localStorage.setItem('user', JSON.stringify(data));
    return data;
  };

  const signOut = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('tokenType');
    localStorage.removeItem('user');
    setUser(null);
  };

  const value = useMemo(
    () => ({ user, setUser, loading, signIn, signOut, refreshUser }),
    [user, loading],
  );

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}

export function useAuth() {
  return useContext(AuthContext);
}
