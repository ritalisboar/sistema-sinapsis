import { createRouter, createWebHistory } from 'vue-router'
import LoginView from '../views/LoginView.vue'
import HomeView from '../views/HomeView.vue'
import IncludeView from '../views/IncludeView.vue'
import AlterView from '../views/AlterView.vue'
import PreviewView from '../views/PreviewView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'login',
      component: LoginView
    },
    {
      path: '/home',
      name: 'home',
      component: HomeView
    },
    {
      path: '/home/include',
      name: 'include',
      component: IncludeView
    },
    {
      path: '/home/alter/:code&:name&:latitude&:longitude',
      name: 'alter',
      component: AlterView
    },
    {
      path: '/home/preview/:code&:name&:latitude&:longitude',
      name: 'preview',
      component: PreviewView
    }
  ]
})

export default router
