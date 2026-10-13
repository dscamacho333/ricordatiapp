import { http } from './http';

export async function getTasks() {
  const { data } = await http.get('/task');
  return data;
}

export async function getTask(taskId) {
  const { data } = await http.get(`/task/${taskId}`);
  return data;
}

export async function createTask(payload) {
  const { data } = await http.post('/task', payload);
  return data;
}

export async function updateTask(taskId, payload) {
  const { data } = await http.put(`/task/${taskId}`, payload);
  return data;
}

export async function deleteTask(taskId) {
  await http.delete(`/task/${taskId}`);
}
