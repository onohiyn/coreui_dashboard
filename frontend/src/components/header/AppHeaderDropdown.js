import React, { useCallback, useRef, useState, useEffect } from 'react'
import {
  CAvatar,
  CBadge,
  CButton,
  CDropdown,
  CDropdownDivider,
  CDropdownItem,
  CDropdownMenu,
  CDropdownToggle,
} from '@coreui/react'
import { cilFile, cilLockLocked } from '@coreui/icons'
import CIcon from '@coreui/icons-react'
import { useHistory } from 'react-router-dom'
import httpCommon from 'src/http-common'
import reactIcon from './../../assets/images/avatars/shinhan.png'
const AppHeaderDropdown = () => {
  const history = useHistory()
  const client = useRef({})
  const currentUserName = localStorage.getItem('userName')
  const api = httpCommon
  const header = {
    Connection: 'keep-alive',
    Accept: '*/*',
  }

  const handleClick = () => {
    submitApi()
    localStorage.clear()
    pageload()
  }

  function submitApi() {
    api.defaults.headers.common[`Authorization`] = 'Bearer ' + localStorage.getItem('token')
    api
      .post('/byebye', { userName: currentUserName }, header)
      .then(console.log('logout'))
      .catch((error) => {
        alert('로그인 후 이용해 주세요')
        localStorage.clear()
        history.push('/login')
      })
  }

  const pageload = () => {
    window.location.href = '/index.html'
  }

  return (
    <CDropdown variant="nav-item">
      <CDropdownToggle placement="bottom-end" className="py-0" caret={false}>
        <CAvatar src={reactIcon} size="md" />
      </CDropdownToggle>
      <CDropdownMenu className="pt-0" placement="bottom-end">
        <CDropdownItem href="">
          <CIcon icon={cilFile} className="me-2" />
          Settings
        </CDropdownItem>
        <CDropdownDivider />
        <CDropdownItem>
          <CButton color="link" onClick={handleClick}>
            <CIcon icon={cilLockLocked} className="me-2" />
            로그아웃
          </CButton>
        </CDropdownItem>
      </CDropdownMenu>
    </CDropdown>
  )
}

export default AppHeaderDropdown
