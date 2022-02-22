import { React, useEffect, useState, RNFetchBlob } from 'react'
import { useLocation, useHistory } from 'react-router-dom'
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
  const history = useHistory()
  const location = useLocation()
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
    ContentType: 'application/octet-stream',
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
        history.push('/login')
      })
  }
  const handleSubmit = (uuid, e) => {
    fileDownlod(uuid)
  }

  function fileDownlod(props) {
    api.defaults.headers.common[`Authorization`] = 'Bearer ' + localStorage.getItem('token')
    api
      .post('/filedown', { uuid: props }, { responseType: 'blob' }, header) //responseType blob 없을시 파일 용량이 비정상으로 내려옴
      .then((response) => {
        console.log(window.navigator.appVersion)
        const name = response.headers['content-disposition'].split('fileName=')[1]
        const url = window.URL.createObjectURL(new Blob([response.data]))
        const link = document.createElement('a')
        link.href = url
        const filename = decodeURI(name)
        link.setAttribute('download', filename)
        link.style.cssText = 'display:none'
        document.body.appendChild(link)
        link.click()
        window.URL.revokeObjectURL(url)
        link.remove()
      })
      .catch((error) => {
        console.log(error.message)
        alert('로그인 후 이용해 주세요')
        localStorage.clear()
        history.push('/login')
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
