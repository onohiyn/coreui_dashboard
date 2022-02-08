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

  const [state, setState] = useState([])
  const [result, setResult] = useState([])

  const handleChange = (e) => {
    setState(e.target.value)
  }

  const handleProve = (e) => {
    ProveUser(state)
  }

  const header = {
    Connection: 'keep-alive',
    Accept: '*/*',
  }

  function ProveUser(props) {
    api.defaults.headers.common[`Authorization`] = 'Bearer ' + localStorage.getItem('token')
    api
      .post(
        '/userprove',
        {
          username: props,
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
    return result.map((row) => {
      return (
        <CTableRow key={row.userId}>
          <CTableHeaderCell scope="row">1</CTableHeaderCell>
          <CTableDataCell>
            <CFormCheck
              type="radio"
              name="flexRadioDefault"
              label={row.userId}
              value={row.userId}
              onChange={handleChange}
            />
          </CTableDataCell>
          <CTableDataCell>{row.email}</CTableDataCell>
          <CTableDataCell>
            <CCol xs="auto">
              <CButton type="button" onClick={handleProve}>
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
            <strong>사용자 승인</strong> <small>Users for register</small>
          </CCardHeader>
          <CCardBody>
            <DocsExample>
              <CTable>
                <CTableHead>
                  <CTableRow>
                    <CTableHeaderCell scope="col">No</CTableHeaderCell>
                    <CTableHeaderCell scope="col">User ID</CTableHeaderCell>
                    <CTableHeaderCell scope="col">Email </CTableHeaderCell>
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
