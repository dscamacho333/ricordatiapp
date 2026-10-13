import { http } from './http';

export async function getTaskStatuses() {
  const { data } = await http.get('/task-status');
  return data;
}

export async function getPriorityLevels() {
  const { data } = await http.get('/task-priority-level');
  return data;
}

export async function getReminderTimes() {
  const { data } = await http.get('/task-reminder-time');
  return data;
}

export async function getCategories() {
  const { data } = await http.get('/task-category');
  return data;
}

export async function getCategory(taskCategoryId) {
  const { data } = await http.get(`/task-category/${taskCategoryId}`);
  return data;
}

export async function createCategory(payload) {
  const { data } = await http.post('/task-category', payload);
  return data;
}

export async function updateCategory(taskCategoryId, payload) {
  const { data } = await http.put(`/task-category/${taskCategoryId}`, payload);
  return data;
}

export async function deleteCategory(taskCategoryId) {
  await http.delete(`/task-category/${taskCategoryId}`);
}
