import { http } from './http';

export async function getCurrentUser() {
  const { data } = await http.get('/user');
  return data;
}

export async function getUsers() {
  const { data } = await http.get('/user/all');
  return data;
}

export async function createUser(payload) {
  const { data } = await http.post('/user', payload);
  return data;
}

export async function updateUser(payload) {
  const { data } = await http.put('/user', payload);
  return data;
}

export async function deleteUser() {
  await http.delete('/user');
}
