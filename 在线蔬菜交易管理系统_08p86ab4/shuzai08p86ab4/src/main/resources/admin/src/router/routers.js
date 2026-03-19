/**
 * @description: 路由页面列表
 */

const ListPage = () => import("@/views/list/list.vue");

const routes = [
  {
    path: '/',
    component: () => import('@/views/layout/layout.vue'),
    redirect: '/login',
    children: [
      {
        path: '/home',
        component: () => import('@/views/home/home.vue'),
        meta: {
          title: '首页'
        },        
      },
      {
        path: '/center',
        component: () => import('@/views/center.vue'),
        meta: {
          title: '个人信息'
        },
      },
      {
        path: '/updatePassword',
        component: () => import('@/views/updatePassword.vue'),
        meta: {
          title: '修改密码',
        },
      },
      {
        path: '/config/:type',
        component: ListPage,
      },
      {
        path: '/yonghu',
        component: ListPage,
        meta: {
          title: "用户",
        },        
      },  
      {
        path: '/shangjia',
        component: ListPage,
        meta: {
          title: "商家",
        },        
      },  
      {
        path: '/chanpinfenlei',
        component: ListPage,
        meta: {
          title: "产品分类",
        },        
      },  
      {
        path: '/chandi',
        component: ListPage,
        meta: {
          title: "产地",
        },        
      },  
      {
        path: '/chanpinxinxi',
        component: ListPage,
        meta: {
          title: "产品信息",
        },        
      },  
      {      
        path: '/orders/:status',
        component: ListPage
      },     
      {
        path: '/news',
        component: ListPage,
        meta: {
          title: "蔬菜资讯",
        },        
      },  
      {
        path: '/chatmessage',
        component: ListPage,
        meta: {
          title: "消息表",
        },        
      },  
      {
        path: '/friend',
        component: ListPage,
        meta: {
          title: "好友表",
        },        
      },  
      {
        path: '/cart',
        component: ListPage,
        meta: {
          title: "购物车表",
        },        
      },  
      {
        path: '/address',
        component: ListPage,
        meta: {
          title: "地址",
        },        
      },  
      {
        path: '/chargerecord',
        component: ListPage,
        meta: {
          title: "充值记录表",
        },        
      },  
      {
        path: '/storeup',
        component: ListPage,
        meta: {
          title: "收藏表",
        },        
      },  
      {
        path: '/users',
        component: ListPage,
        meta: {
          title: "管理员",
        },        
      },  
      {
        path: '/discusschanpinxinxi',
        component: ListPage,
        meta: {
          title: "产品信息评论",
        },        
      },  
    ],
  },
  {
    path: '/login',
    component: () => import('@/views/login/login.vue'),
    meta: {
      title: "登录",
    },
  },
  {
    path: '/register',
    component: () => import('@/views/register/register.vue'),
    meta: {
      title: "注册",
    },
  },
  {
    path: '/forgetPassword',
    component: () => import('@/views/forgetPassword.vue'),
    meta: {
      title: '忘记密码',
    },
  },
]


export default routes