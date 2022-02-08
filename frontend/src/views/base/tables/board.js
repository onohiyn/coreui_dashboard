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
  CFormInput,
  CFormLabel,
  CFormTextarea,
} from '@coreui/react'
import { DocsExample } from 'src/components'

import { useHistory } from 'react-router-dom'
import httpCommon from 'src/http-common'

function Board() {
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

  const handleClick = () => {
    history.push('/board_write')
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
                  <CTableHeaderCell scope="col">내용</CTableHeaderCell>
                  <CTableHeaderCell scope="col">첨부파일</CTableHeaderCell>
                </CTableRow>
              </CTableHead>
              <CTableBody></CTableBody>
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
