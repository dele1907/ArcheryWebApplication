import { createRouter, createWebHistory } from 'vue-router'
import DashboardPage from '@/components/pages/DashboardPage.vue'

const routes = [{ path: '/', component: DashboardPage }]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
})

export default router
