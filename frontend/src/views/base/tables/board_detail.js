import { React, useEffect, useState, useHistory } from 'react'
import { useLocation } from 'react-router-dom'
import {
  CCard,
  CCardBody,
  CCardHeader,
  CCol,
  CRow,
  CAccordion,
  CAccordionBody,
  CAccordionHeader,
  CAccordionItem,
  CButton,
} from '@coreui/react'
import { DocsExample } from 'src/components'
import httpCommon from 'src/http-common'

function Accordion() {
  const location = useLocation()
  //const history = useHistory()
  const contentId = location.state.detail
  const [title, setTitle] = useState('')
  const [text, setText] = useState('')
  const [file, setFile] = useState('')
  const [uuid, setUUID] = useState('')
  const [date, setDate] = useState('')

  const api = httpCommon
  const header = {
    Connection: 'keep-alive',
    Accept: '*/*',
  }

  function submitApi(props) {
    api.defaults.headers.common[`Authorization`] = 'Bearer ' + localStorage.getItem('token')
    api
      .post(
        '/boarddetail',
        {
          _id: props,
        },
        header,
      )
      .then((response) => {
        setTitle(response.data.title)
        setDate(response.data.date)
        setText(response.data.text)
        setFile(response.data.filename)
        setUUID(response.data.fileuuid)
      })
      .catch((error) => {
        alert('로그인 후 이용해 주세요')
        localStorage.clear()
        //history.push('/login')
      })
  }
  const handleSubmit = (uuid, e) => {
    fileDownlod(uuid)
  }

  function fileDownlod(props) {
    api.responseType = 'blob'
    api.defaults.headers.common[`Authorization`] = 'Bearer ' + localStorage.getItem('token')
    api
      .post('/filedownload', { uuid: props }, header)
      .then((res) => {
        const url = window.URL.createObjectURL(new Blob([res.data]))
        const link = document.createElement('a')
        const contentDisposition = res.headers['content-disposition'] // 파일 이름
        const name = res.headers
        console.log(name)
        console.log(contentDisposition)
        let fileName = 'unknown'
        if (contentDisposition) {
          const [fileNameMatch] = contentDisposition
            .split(';')
            .filter((str) => str.includes('filename'))
          if (fileNameMatch) [, fileName] = fileNameMatch.split('=')
        }
        link.href = url
        link.setAttribute('download', `${fileName}`)
        link.style.cssText = 'display:none'
        document.body.appendChild(link)
        link.click()
        link.remove()
      })
      .catch((error) => {
        //alert('로그인 후 이용해 주세요')
        //localStorage.clear()
        //history.push('/login')
      })
  }

  useEffect(() => {
    submitApi(contentId)
  }, [])

  return (
    <CRow>
      <CCol xs={12}>
        <CCard className="mb-4">
          <CCardHeader>
            <strong>{title}</strong>
            <small>
              <p className="text-medium-emphasis small">
                작성일: {date.substring(0, 4) + '년 '}
                {date.substring(4, 6) + '월 '}
                {date.substring(6, 8) + '일'}
              </p>
            </small>
          </CCardHeader>
          <CCardBody>
            <DocsExample href="components/accordion">
              <CAccordion activeItemKey={1}>
                <CAccordionItem itemKey={1}>
                  <CAccordionHeader>내용</CAccordionHeader>
                  <CAccordionBody>{text}</CAccordionBody>
                </CAccordionItem>
                <CAccordionItem itemKey={1}>
                  <CAccordionHeader>첨부파일</CAccordionHeader>

                  {file === '파일없음' ? (
                    <CAccordionBody>{file}</CAccordionBody>
                  ) : (
                    <CAccordionBody>
                      <CButton
                        color="link"
                        onClick={(e) => {
                          handleSubmit(uuid)
                        }}
                      >
                        {file}
                      </CButton>
                    </CAccordionBody>
                  )}
                </CAccordionItem>
              </CAccordion>
            </DocsExample>
          </CCardBody>
        </CCard>
      </CCol>
    </CRow>
  )
}

export default Accordion