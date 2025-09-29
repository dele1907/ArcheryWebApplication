import { createRouter, createWebHistory } from 'vue-router';
import DashboardPage from '@/components/pages/DashboardPage.vue';
import ShootersManagementPage from '@/components/pages/ShootersManagementPage.vue';
import ClubsManagementPage from '@/components/pages/ClubsManagementPage.vue';

const routes = [
  { path: '/', component: DashboardPage, meta: { title: 'Dashboard' } },
  {
    path: '/shooters',
    component: ShootersManagementPage,
    meta: { title: 'Schützenverwaltung' },
  },
  {
    path: '/clubs',
    component: ClubsManagementPage,
    meta: { title: 'Vereine' },
  },
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
});

export default router;
