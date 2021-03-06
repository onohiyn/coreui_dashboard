import React, { useState, useEffect } from 'react'
import {
  CCard,
  CCardBody,
  CCardHeader,
  CCol,
  CRow,
  CTable,
  CTableBody,
  CTableDataCell,
  CTableHead,
  CTableHeaderCell,
  CTableRow,
  CButton,
  CFormCheck,
} from '@coreui/react'

import { DocsExample } from 'src/components'
import { useHistory } from 'react-router-dom'
import httpCommon from 'src/http-common'

function Userregister() {
  const history = useHistory()
  const api = httpCommon
  const [result, setResult] = useState([])
  const [role, setRole] = useState('user')

  const handleProve = (props, role, e) => {
    ProveUser(props, role)
  }

  const header = {
    Connection: 'keep-alive',
    Accept: '*/*',
  }

  function ProveUser(props, role) {
    api.defaults.headers.common[`Authorization`] = 'Bearer ' + localStorage.getItem('token')
    api
      .post(
        '/userprove',
        {
          userId: props,
          role: role,
        },
        header,
      )
      .then(submitApi)
      .catch((error) => {
        alert('로그인 후 이용해 주세요')
        localStorage.removeItem('token')
        localStorage.removeItem('role')
        history.push('/login')
      })
  }

  function submitApi() {
    api.defaults.headers.common[`Authorization`] = 'Bearer ' + localStorage.getItem('token')
    api
      .post('/userlist', {}, header)
      .then((json) => setResult(json.data))
      .catch((error) => {
        alert('로그인 후 이용해 주세요')
        localStorage.clear()
        history.push('/login')
      })
  }

  useEffect(() => {
    submitApi()
    return () => {}
  }, [])

  const rederTable = () => {
    return result.map((row, index) => {
      return (
        <CTableRow key={row.userId}>
          <CTableHeaderCell scope="row">{index + 1}</CTableHeaderCell>
          <CTableDataCell>{row.userId}</CTableDataCell>
          <CTableDataCell>{row.email}</CTableDataCell>
          <CTableDataCell>
            <CFormCheck
              type="radio"
              name={index}
              value="admin"
              label="Y"
              onChange={(e) => setRole('admin')}
            />
            <CFormCheck
              type="radio"
              name={index}
              value="user"
              label="N"
              onChange={(e) => setRole('user')}
              defaultChecked
            />
          </CTableDataCell>
          <CTableDataCell>
            <CCol xs="auto">
              <CButton type="button" onClick={(e) => handleProve(row.userId, role)}>
                승인
              </CButton>
            </CCol>
          </CTableDataCell>
        </CTableRow>
      )
    })
  }

  return (
    <CRow>
      <CCol xs={12}>
        <CCard className="mb-4">
          <CCardHeader>
            <strong>사용자 승인</strong> <small>User list</small>
          </CCardHeader>
          <CCardBody>
            <DocsExample>
              <CTable>
                <CTableHead>
                  <CTableRow>
                    <CTableHeaderCell scope="col">No.</CTableHeaderCell>
                    <CTableHeaderCell scope="col">User ID</CTableHeaderCell>
                    <CTableHeaderCell scope="col">Email </CTableHeaderCell>
                    <CTableHeaderCell scope="col">Admin</CTableHeaderCell>
                    <CTableHeaderCell scope="col"></CTableHeaderCell>
                  </CTableRow>
                </CTableHead>
                <CTableBody>{rederTable()}</CTableBody>
              </CTable>
            </DocsExample>
          </CCardBody>
        </CCard>
      </CCol>
    </CRow>
  )
}
export default Userregister
