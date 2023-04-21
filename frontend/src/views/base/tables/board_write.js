import React, { useState } from 'react'
import {
  CCardBody,
  CCardHeader,
  CCol,
  CRow,
  CButton,
  CForm,
  CFormInput,
  CFormLabel,
  CFormTextarea,
} from '@coreui/react'

import { useHistory } from 'react-router-dom'
import httpCommon from 'src/http-common'
import { CKEditor } from '@ckeditor/ckeditor5-react'
import ClassicEditor from '@ckeditor/ckeditor5-build-classic'

function Write() {
  const [title, setTitle] = useState('제목 없음')
  const [text, setText] = useState('')
  const [file, setFile] = useState(null)
  const history = useHistory()
  const userId = localStorage.getItem('userId')
  const api = httpCommon
  const formData = new FormData()
  const boardData = { title: title, text: text, userId: userId }

  const handleSubmit = (e) => {
    if (file === null) {
    } else {
      formData.append('file', file[0])
    }
    formData.append('board', new Blob([JSON.stringify(boardData)], { type: 'application/json' }))
    Submit(formData)
    e.preventDefault()
  }
  const header = {
    Connection: 'keep-alive',
    Accept: '*/*',
    ContentType: 'multipart/form-data',
  }

  function Submit(props) {
    api.defaults.headers.common[`Authorization`] = 'Bearer ' + localStorage.getItem('token')
    api
      .post('/boardWrite', props, header)
      .then((response) => {
        history.push('board')
      })
      .catch((error) => {
        localStorage.clear()
        history.push('/login')
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
                onChange={(e) => setTitle(e.target.value)}
              />
              <CKEditor
                editor={ClassicEditor}
                onReady={(editor) => {
                  editor.editing.view.change((writer) => {
                    writer.setStyle('height', '300px', editor.editing.view.document.getRoot())
                  })
                }}
                onChange={(event, editor) => setText(editor.getData())}
                onBlur={(event, editor) => {}}
                onFocus={(event, editor) => {}}
              />

              <CFormInput
                type="file"
                multiple
                id="file"
                onChange={(e) => setFile(e.target.files)}
              />
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
