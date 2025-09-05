import { createRouter, createWebHistory } from 'vue-router'
import DashboardPage from '@/components/pages/DashboardPage.vue'
import ShootersManagementPage from '@/components/pages/ShootersManagementPage.vue'

const routes = [
  { path: '/', component: DashboardPage, meta: { title: 'Dashboard' } },
  {
    path: '/shootersmanagment',
    component: ShootersManagementPage,
    meta: { title: 'Schützenverwaltung' },
  },
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
})

export default router
