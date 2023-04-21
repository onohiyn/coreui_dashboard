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
  CInputGroup,
  CFormSelect,
} from '@coreui/react'
import { DocsExample } from 'src/components'
import { useHistory } from 'react-router-dom'
import httpCommon from 'src/http-common'
import Pagination from './pagination'

function Board() {
  const [result, setResult] = useState([])
  const [searchTerm, setSearchTerm] = useState([])
  const [searchTopic, setSearchTopic] = useState('title')
  const history = useHistory()
  const [limit, setLimit] = useState(10)
  const [page, setPage] = useState(1)
  const offset = (page - 1) * limit
  const [resultIndex, setResultIndex] = useState(Object.keys(result).length)
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

  const handleSubmit = (content, e) => {
    submitContent(content)
  }

  function submitContent(props) {
    history.push({
      pathname: '/board_detail',
      state: { detail: props },
    })
  }
  function handleDelete(props, userId) {
    if (window.confirm('삭제')) {
      if (userId == localStorage.getItem('userId') || userId == 'admin') {
        Delete(props)
      } else {
        alert('권한없음')
      }
    } else {
    }
  }
  function Delete(props) {
    api.defaults.headers.common[`Authorization`] = 'Bearer ' + localStorage.getItem('token')
    api
      .post(
        '/boardDelete',
        {
          _id: props,
        },
        {
          headers: {
            'Content-Type': 'application/json',
            Connection: 'keep-alive',
            Accept: '*/*',
          },
        },
      )
      .then(submitApi)
      .catch((error) => {
        localStorage.clear()
        history.push('/login')
      })
  }

  useEffect(() => {
    submitApi()
    return () => {}
  }, [])

  const rederTable = () => {
    return result
      .filter((val) => {
        if (searchTerm == '') {
          return val
        } else if (
          searchTopic == 'title' &&
          val.title.toLowerCase().includes(searchTerm.toLowerCase())
        ) {
          return val
        } else if (
          searchTopic == 'userId' &&
          val.userId.toLowerCase().includes(searchTerm.toLowerCase())
        ) {
          return val
        }
      })
      .slice(offset, offset + limit)
      .map((row, index) => {
        return (
          <CTableRow key={index}>
            <CTableDataCell>{index + 1}</CTableDataCell>
            <CTableDataCell>{row.userId}</CTableDataCell>
            <CTableDataCell>
              <CFormInput type="hidden" value={row.id} />
              <CButton
                color="link"
                onClick={(e) => {
                  handleSubmit(row.id, e)
                }}
              >
                {row.title}
              </CButton>
            </CTableDataCell>
            <CTableDataCell>
              {row.date.substring(0, 4) + '년 '}
              {row.date.substring(4, 6) + '월 '}
              {row.date.substring(6, 8) + '일'}
            </CTableDataCell>
            <CTableDataCell>
              <CButton
                color="danger"
                onClick={(e) => {
                  handleDelete(row.id, row.userId)
                }}
              >
                삭제
              </CButton>
            </CTableDataCell>
          </CTableRow>
        )
      })
  }

  return (
    <CRow>
      <CCard className="mb-4">
        <CCardHeader>
          <strong>게시판</strong> <small>list</small>
        </CCardHeader>
        <CCardBody>
          <DocsExample>
            <CRow className="g-3">
              <CInputGroup size="sm" className="mb-3">
                <CCol md={1.5}>
                  <CFormSelect
                    id="inputGroupSelect01"
                    onChange={(e) => setSearchTopic(e.target.value)}
                  >
                    <option value="title">제목</option>
                    <option value="userId">작성자</option>
                  </CFormSelect>
                </CCol>
                <CCol md={4}>
                  <CFormInput
                    id="search"
                    placeholder="Search"
                    onChange={(e) => {
                      setSearchTerm(e.target.value)
                    }}
                  ></CFormInput>
                </CCol>
              </CInputGroup>
            </CRow>
            <CTable>
              <CTableHead>
                <CTableRow>
                  <CTableHeaderCell scope="col">번호</CTableHeaderCell>
                  <CTableHeaderCell scope="col">작성자</CTableHeaderCell>
                  <CTableHeaderCell scope="col">제목</CTableHeaderCell>
                  <CTableHeaderCell scope="col">작성일</CTableHeaderCell>
                </CTableRow>
              </CTableHead>
              <CTableBody>{rederTable()}</CTableBody>
            </CTable>
            <Pagination
              resultIndex={result.length}
              limit={limit}
              page={page}
              setPage={setPage}
            ></Pagination>
            <CCol>
              <div className="d-grid gap-2 d-md-flex justify-content-md-end">
                <CButton onClick={handleClick}> 글쓰기</CButton>
              </div>
            </CCol>
          </DocsExample>
        </CCardBody>
      </CCard>
    </CRow>
  )
}
export default Board
