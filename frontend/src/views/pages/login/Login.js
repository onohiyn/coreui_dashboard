import React, { useState, useEffect } from 'react'
import { Link, useHistory } from 'react-router-dom'
import {
  CButton,
  CCard,
  CCardBody,
  CCardGroup,
  CCol,
  CContainer,
  CForm,
  CFormInput,
  CInputGroup,
  CInputGroupText,
  CRow,
} from '@coreui/react'
import CIcon from '@coreui/icons-react'
import { cilLockLocked, cilUser } from '@coreui/icons'
import httpCommon from 'src/http-common'

function Login() {
  const api = httpCommon
  const headers = {
    Connection: 'keep-alive',
    'Content-type': 'application/json; charset=UTF-8',
    Accept: '*/*',
  }

  const [username, setUsername] = useState()
  const [passwd, setPasswd] = useState()

  const handleSubmit = (e) => {
    Submit({ username, passwd })
    e.preventDefault()
  }
  const history = useHistory()
  function Submit({ username, passwd }) {
    api
      .post(
        '/authenticate',
        {
          username: username,
          password: passwd,
        },
        headers,
      )
      .then((response) => {
        const accessToken = response.data.token
        const role = response.data.role
        const userId = response.data.userId
        localStorage.setItem('token', accessToken)
        localStorage.setItem('role', role)
        localStorage.setItem('userId', userId)
        history.push('/dashboard')
      })
      .catch((error) => {
        console.log(error.response)
        localStorage.clear()
        alert('로그인 실패! ID/PWD 확인해주세요.', error)
      })
  }
  useEffect(() => {
    localStorage.clear()
  }, [])
  return (
    <div className="bg-light min-vh-100 d-flex flex-row align-items-center">
      <CContainer>
        <CRow className="justify-content-center">
          <CCol md={8}>
            <CCardGroup>
              <CCard className="p-4">
                <CCardBody>
                  <CForm onSubmit={handleSubmit}>
                    <h1>Login</h1>
                    <p className="text-medium-emphasis">Sign In to your account</p>
                    <CInputGroup className="mb-3">
                      <CInputGroupText>
                        <CIcon icon={cilUser} />
                      </CInputGroupText>
                      <CFormInput
                        placeholder="Username"
                        autoComplete="username"
                        onChange={(e) => setUsername(e.target.value)}
                      />
                    </CInputGroup>
                    <CInputGroup className="mb-4">
                      <CInputGroupText>
                        <CIcon icon={cilLockLocked} />
                      </CInputGroupText>
                      <CFormInput
                        type="password"
                        placeholder="Password"
                        autoComplete="current-password"
                        onChange={(e) => setPasswd(e.target.value)}
                      />
                    </CInputGroup>
                    <CRow>
                      <CCol xs={6}>
                        <CButton color="primary" className="px-4" type="submit">
                          Login
                        </CButton>
                      </CCol>
                      <CCol xs={6} className="text-right">
                        <CButton color="link" className="px-0">
                          Forgot password?
                        </CButton>
                      </CCol>
                    </CRow>
                  </CForm>
                </CCardBody>
              </CCard>
              <CCard className="text-white bg-primary py-5" style={{ width: '100%' }}>
                <CCardBody className="text-center">
                  <div>
                    <h2>Sign up</h2>
                    <p>신규 회원가입</p>
                    <p>관리자 승인까지 기다려 주세요!</p>
                    <Link to="/register">
                      <CButton color="primary" className="mt-3" active tabIndex={-1}>
                        Register Now!
                      </CButton>
                    </Link>
                  </div>
                </CCardBody>
              </CCard>
            </CCardGroup>
          </CCol>
        </CRow>
      </CContainer>
    </div>
  )
}

export default Login
