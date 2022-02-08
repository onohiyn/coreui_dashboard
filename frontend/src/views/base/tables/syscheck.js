import React, { useState } from 'react'
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
  CFormSelect,
  CButton,
  CForm,
} from '@coreui/react'
import { DocsExample } from 'src/components'

import { useHistory } from 'react-router-dom'
import httpCommon from 'src/http-common'

function Syscheck1() {
  const [posts, setPosts] = useState('default')
  const [state, setState] = useState('test')
  const result = { result: 'dd' }
  const history = useHistory()
  const api = httpCommon

  const handleChange = (e) => {
    setState(e.target.value)
  }

  const handleSubmit = (e) => {
    SubmitTest(state)
    e.preventDefault()
  }
  const header = {
    Connection: 'keep-alive',
    Accept: '*/*',
  }
  function SubmitTest(props) {
    api.defaults.headers.common[`Authorization`] = 'Bearer ' + localStorage.getItem('token')
    api
      .post(
        '/api/check',
        {
          userId: props,
        },
        header,
      )
      .then((response) => {
        result.result = response.data.test
        setPosts(response.data.test)
      })
      .catch((error) => {
        localStorage.removeItem('token')
        history.push('/login')
      })
  }

  return (
    <CRow>
      <CCol xs={12}>
        <CCard className="mb-4">
          <CCardHeader>
            <strong>System Check</strong> <small>was check</small>
          </CCardHeader>
          <CCardBody>
            <DocsExample>
              <CForm className="row gy-2 gx-3 align-items-center" onSubmit={handleSubmit}>
                <CCol xs="auto">
                  <CFormSelect id="autoSizingSelect" value={state.value} onChange={handleChange}>
                    <option defaultValue="Test">Test</option>
                    <option>Prod</option>
                  </CFormSelect>
                </CCol>
                <CCol xs="auto">
                  <CButton type="submit">Submit</CButton>
                </CCol>
              </CForm>
              <CCol xs="auto"></CCol>
              <CTable>
                <CTableHead>
                  <CTableRow>
                    <CTableHeaderCell scope="col">{posts}</CTableHeaderCell>
                    <CTableHeaderCell scope="col">{state}</CTableHeaderCell>
                    <CTableHeaderCell scope="col">{result.result}</CTableHeaderCell>
                    <CTableHeaderCell scope="col">ss</CTableHeaderCell>
                  </CTableRow>
                </CTableHead>
                <CTableBody>
                  <CTableRow>
                    <CTableHeaderCell scope="row">1</CTableHeaderCell>
                    <CTableDataCell>Mark</CTableDataCell>
                    <CTableDataCell>Otto</CTableDataCell>
                    <CTableDataCell>@mdo</CTableDataCell>
                  </CTableRow>
                  <CTableRow>
                    <CTableHeaderCell scope="row">2</CTableHeaderCell>
                    <CTableDataCell>Jacob</CTableDataCell>
                    <CTableDataCell>Thornton</CTableDataCell>
                    <CTableDataCell>@fat</CTableDataCell>
                  </CTableRow>
                  <CTableRow>
                    <CTableHeaderCell scope="row">3</CTableHeaderCell>
                    <CTableDataCell colSpan="2">Larry the Bird</CTableDataCell>
                    <CTableDataCell>@twitter</CTableDataCell>
                  </CTableRow>
                </CTableBody>
              </CTable>
            </DocsExample>
          </CCardBody>
        </CCard>
      </CCol>
    </CRow>
  )
}
export default Syscheck1
