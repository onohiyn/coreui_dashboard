import React from 'react'
import CIcon from '@coreui/icons-react'
import { cilClipboard } from '@coreui/icons'
import { CNavItem } from '@coreui/react'

const _nav = [
  {
    component: CNavItem,
    name: 'Board',
    to: '/board',
    icon: <CIcon icon={cilClipboard} customClassName="nav-icon" />,
  },
]

export default _nav
