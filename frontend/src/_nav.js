import React from 'react'
import CIcon from '@coreui/icons-react'
import {
  cilClipboard,
  cilHappy,
  cilCopy,
  cilUserFollow,
  cilMagnifyingGlass,
  cilChatBubble,
  cilMonitor,
  cilUser,
} from '@coreui/icons'
import { CNavItem } from '@coreui/react'

const _nav = [
  {
    component: CNavItem,
    name: 'Board',
    to: '/board',
    icon: <CIcon icon={cilClipboard} customClassName="nav-icon" />,
  },
  {
    component: CNavItem,
    name: 'Link',
    to: '/linkregister',
    icon: <CIcon icon={cilMagnifyingGlass} customClassName="nav-icon" />,
  },
  {
    component: CNavItem,
    name: 'System Check',
    to: '/syscheck',
    icon: <CIcon icon={cilHappy} customClassName="nav-icon" />,
  },
  {
    component: CNavItem,
    name: 'Monitoring',
    to: '/monitoring',
    icon: <CIcon icon={cilMonitor} customClassName="nav-icon" />,
  },
  {
    component: CNavItem,
    name: 'System Register',
    to: '/systemregister',
    icon: <CIcon icon={cilCopy} customClassName="nav-icon" />,
  },
  {
    component: CNavItem,
    name: 'User List',
    to: '/userall',
    icon: <CIcon icon={cilUser} customClassName="nav-icon" />,
  },
  {
    component: CNavItem,
    name: 'User Register',
    to: '/userregister',
    icon: <CIcon icon={cilUserFollow} customClassName="nav-icon" />,
  },
]

export default _nav
