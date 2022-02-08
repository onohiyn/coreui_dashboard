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

function Write() {
  const [title, setTitle] = useState()
  const [text, setText] = useState()
  const [file, setFile] = useState()
  const history = useHistory()
  const userId = localStorage.getItem('userId')
  const api = httpCommon

  const handleChangeTitle = (e) => {
    setTitle(e.target.value)
  }
  const handleChangeText = (e) => {
    setText(e.target.value)
  }
  const onSaveFiles = (e) => {
    setFile(e.target.file)
  }
  const handleSubmit = (e) => {
    Submit({ title, text, userId, file })
    e.preventDefault()
  }
  const header = {
    Connection: 'keep-alive',
    Accept: '*/*',
  }
  function Submit(title, text, file, userId) {
    api.defaults.headers.common[`Authorization`] = 'Bearer ' + localStorage.getItem('token')
    api
      .post(
        '/boardWrite',
        {
          userId: userId,
          title: title,
          text: text,
          //file: file,
        },
        header,
      )
      .then((response) => {})
      .catch((error) => {
        //localStorage.clear()
        //history.push('/login')
      })
  }

  return (
    <CRow>
      <CCardHeader>
        <strong>게시물 등록</strong>
      </CCardHeader>
      <CCol>
        <CCardBody>
          <CForm onSubmit={handleSubmit}>
            <div className="mb-3">
              <CFormLabel htmlFor="TextArea">내용</CFormLabel>
              <CFormInput
                type="text"
                size="sm"
                placeholder="제목"
                aria-label="sm input example"
                onChange={handleChangeTitle}
              />
              <CFormTextarea id="TextArea" rows="3" onChange={handleChangeText}></CFormTextarea>
              {/*<CFormInput type="file" multiple id="formFile" onChange={onSaveFiles} />*/}
            </div>
            <div className="d-grid gap-2 d-md-flex justify-content-md-end">
              <CButton type="submit">등록</CButton>
            </div>
          </CForm>
        </CCardBody>
      </CCol>
    </CRow>
  )
}
export default Write
