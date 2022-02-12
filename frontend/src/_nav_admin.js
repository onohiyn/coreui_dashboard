import React from 'react'
import CIcon from '@coreui/icons-react'
import { cilSpeedometer, cilUserFollow, cilClipboard } from '@coreui/icons'
import { CNavItem, CNavTitle } from '@coreui/react'

const _nav = [
  {
    component: CNavItem,
    name: 'Dashboard',
    to: '/dashboard',
    icon: <CIcon icon={cilSpeedometer} customClassName="nav-icon" />,
    badge: {
      color: 'info',
      text: 'NEW',
    },
  },
  {
    component: CNavTitle,
    name: 'Custom Pages',
  },
  {
    component: CNavItem,
    name: 'Board',
    to: '/board',
    icon: <CIcon icon={cilClipboard} customClassName="nav-icon" />,
  },
  {
    component: CNavItem,
    name: 'User Register',
    to: '/userregister',
    icon: <CIcon icon={cilUserFollow} customClassName="nav-icon" />,
  },
]

export default _nav
