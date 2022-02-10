import React, { useState } from 'react'
import {
  CButton,
  CCard,
  CCardBody,
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
import { useHistory } from 'react-router-dom'
import httpCommon from 'src/http-common'

function Register() {
  const [username, setUsername] = useState()
  const [passwd, setPasswd] = useState()
  const [passwdConfirm, setPasswdConfirm] = useState()
  const [email, setEmail] = useState()

  const api = httpCommon

  const history = useHistory()
  const handleSubmit = (e) => {
    Submit({ username, passwd, email, passwdConfirm })
    e.preventDefault()
  }

  function Submit({ username, passwd, email, passwdConfirm }) {
    if (passwd === passwdConfirm) {
      api
        .post('/signup', {
          username: username,
          password: passwd,
          email: email,
        })
        .then((response) => {
          if (response.data.result === 'success') {
            alert('회원가입 성공! 로그인 페이지로 이동합니다.')
            history.push('/login')
          } else {
            alert('ID가 중복됩니다. 새로운 ID로 시도해주세요')
          }
        })
        .catch((error) => {
          alert('회원가입 실패!', error)
        })
    } else {
      alert('비밀번호 중복 오류')
    }
  }

  return (
    <div className="bg-light min-vh-100 d-flex flex-row align-items-center">
      <CContainer>
        <CRow className="justify-content-center">
          <CCol md={9} lg={7} xl={6}>
            <CCard className="mx-4">
              <CCardBody className="p-4">
                <CForm onSubmit={handleSubmit}>
                  <h1>Register</h1>
                  <p className="text-medium-emphasis">Create your account</p>
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
                  <CInputGroup className="mb-3">
                    <CInputGroupText>@</CInputGroupText>
                    <CFormInput
                      placeholder="Email"
                      autoComplete="email"
                      onChange={(e) => setEmail(e.target.value)}
                    />
                  </CInputGroup>
                  <CInputGroup className="mb-3">
                    <CInputGroupText>
                      <CIcon icon={cilLockLocked} />
                    </CInputGroupText>
                    <CFormInput
                      type="password"
                      placeholder="Password"
                      autoComplete="new-password"
                      onChange={(e) => setPasswd(e.target.value)}
                    />
                  </CInputGroup>
                  <CInputGroup className="mb-4">
                    <CInputGroupText>
                      <CIcon icon={cilLockLocked} />
                    </CInputGroupText>
                    <CFormInput
                      type="password"
                      placeholder="Repeat password"
                      autoComplete="new-password"
                      onChange={(e) => setPasswdConfirm(e.target.value)}
                    />
                  </CInputGroup>
                  <div className="d-grid">
                    <CButton color="success" type="submit">
                      Create Account
                    </CButton>
                  </div>
                </CForm>
              </CCardBody>
            </CCard>
          </CCol>
        </CRow>
      </CContainer>
    </div>
  )
}

export default Register
