import React from 'react'
import { useSelector, useDispatch } from 'react-redux'

import { CSidebar, CSidebarBrand, CSidebarNav, CSidebarToggler, CAvatar } from '@coreui/react'
import CIcon from '@coreui/icons-react'
import { cilStar, cilSun, cilMoon } from '@coreui/icons'

import { AppSidebarNav } from './AppSidebarNav'

import { sygnet } from 'src/assets/brand/sygnet'

import SimpleBar from 'simplebar-react'
import 'simplebar/dist/simplebar.min.css'

// sidebar nav config
import navigation from '../_nav'
import navigationAdmin from '../_nav'
//import reactIcon from './../assets/images/avatars/shin2.png'

const AppSidebar = () => {
  const dispatch = useDispatch()
  const unfoldable = useSelector((state) => state.sidebarUnfoldable)
  const sidebarShow = useSelector((state) => state.sidebarShow)
  let role = localStorage.getItem('role')

  return (
    <CSidebar
      position="fixed"
      unfoldable={unfoldable}
      visible={sidebarShow}
      onVisibleChange={(visible) => {
        dispatch({ type: 'set', sidebarShow: visible })
      }}
    >
      <CSidebarBrand className="d-none d-md-flex" to="/">
        <CIcon icon={cilStar} />
        <pre> </pre>
        <CIcon icon={cilSun} />
        <pre> </pre>
        <CIcon icon={cilMoon} />
        <CIcon className="sidebar-brand-narrow" icon={sygnet} height={35} />
      </CSidebarBrand>
      <CSidebarNav>
        <SimpleBar>
          {role === 'admin' ? (
            <AppSidebarNav items={navigationAdmin} />
          ) : (
            <AppSidebarNav items={navigation} />
          )}
        </SimpleBar>
      </CSidebarNav>
      <CSidebarToggler
        className="d-none d-lg-flex"
        onClick={() => dispatch({ type: 'set', sidebarUnfoldable: !unfoldable })}
      />
    </CSidebar>
  )
}

export default React.memo(AppSidebar)
