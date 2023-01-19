// Composables
import {createRouter, createWebHistory} from 'vue-router'

const routes = [
  {
    path: '/',
    component: () => import('@/layouts/default/Default.vue'),
    children: [
      {
        path: '',
        redirect: '/todo/list',
        component: () => import(/* webpackChunkName: "home" */ '@/views/Home.vue'),
      },
      {
        path: '/todo/list',
        name: 'TodoListPage',
        component: () => import(/* webpackChunkName: "home" */ '@/views/todo/TodoListPage.vue'),
      },
      {
        path: '/todo/:id',
        name: 'TodoPage',
        meta: ['auth', 'todo'],
        component: () => import(/* webpackChunkName: "home" */ '@/views/todo/TodoPage.vue'),
      },
      {
        path: '/login',
        name: 'LoginPage',
        component: () => import(/* webpackChunkName: "home" */ '@/views/LoginPage.vue'),
      },
      {
        path: '/sampleTest',
        name: 'SampleTest',
        component: () => import('@/views/SampleTestPage.vue'),
      }
    ],
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
})

router.beforeEach((to) => {
  // 인증이 필요한 페이지에접근 했지만, localstorage 에 access 토큰이 없는 경우 로그인 페이지로 튕김
  if (Object.values(to.meta).indexOf('auth') == -1) {
    return
  }
  const access = localStorage.getItem('access')
  if (access == null) {
    router.push({name: 'LoginPage'})
  }
});

export default router
