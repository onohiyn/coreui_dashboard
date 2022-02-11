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
  CFormInput,
} from '@coreui/react'
import { DocsExample } from 'src/components'

import { useHistory } from 'react-router-dom'
import httpCommon from 'src/http-common'

function Board() {
  const [result, setResult] = useState([])
  const history = useHistory()
  const api = httpCommon
  const header = {
    Connection: 'keep-alive',
    Accept: '*/*',
  }
  const handleClick = () => {
    history.push('/board_write')
  }

  function submitApi() {
    api.defaults.headers.common[`Authorization`] = 'Bearer ' + localStorage.getItem('token')
    api
      .post('/boardcontents', {}, header)
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
          <CTableDataCell>{row.userId}</CTableDataCell>
          <CTableDataCell>
            <CFormInput type="hidden" value={row.id} />
            <CButton color="link">{row.title}</CButton>
          </CTableDataCell>
          <CTableDataCell>
            {row.date.substring(0, 4) + '년 '}
            {row.date.substring(4, 6) + '월 '}
            {row.date.substring(6, 8) + '일'}
          </CTableDataCell>
        </CTableRow>
      )
    })
  }

  return (
    <CRow>
      <CCard className="mb-4">
        <CCardHeader>
          <strong>게시판</strong> <small>게시물</small>
        </CCardHeader>
        <CCardBody>
          <DocsExample>
            <CTable>
              <CTableHead>
                <CTableRow>
                  <CTableHeaderCell scope="col">작성자</CTableHeaderCell>
                  <CTableHeaderCell scope="col">제목</CTableHeaderCell>
                  <CTableHeaderCell scope="col">작성일</CTableHeaderCell>
                </CTableRow>
              </CTableHead>
              <CTableBody>{rederTable()}</CTableBody>
            </CTable>
          </DocsExample>
        </CCardBody>
        <CCol>
          <DocsExample href="components/buttons#block-buttons">
            <div className="d-grid gap-2 d-md-flex justify-content-md-end">
              <CButton onClick={handleClick}> 글쓰기</CButton>
            </div>
          </DocsExample>
        </CCol>
      </CCard>
    </CRow>
  )
}
export default Board
