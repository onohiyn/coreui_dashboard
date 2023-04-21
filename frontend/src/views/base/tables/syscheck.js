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
  CFormSelect,
  CButton,
  CForm,
} from '@coreui/react'

import { useHistory } from 'react-router-dom'
import httpCommon from 'src/http-common'
import { motion } from 'framer-motion'
import { DocsExample } from 'src/components'
import './style.css'

function Syscheck() {
  const [system, setSystem] = useState(['select'])
  const [result, setResult] = useState([])

  const history = useHistory()
  const api = httpCommon

  const handleChange = (event) => {
    setSystem(event.target.value)
  }

  const handleSubmit = (e) => {
    if (system === 'select') {
      alert('시스템을 선택해주세요')
    } else {
      Submit(system)
    }
  }
  const header = {
    Connection: 'keep-alive',
    Accept: '*/*',
  }

  const draw = {
    hidden: { pathLength: 0, opacity: 0 },
    visible: (i) => {
      const delay = 1 + i * 0.5
      return {
        pathLength: 1,
        opacity: 1,
        transition: {
          pathLength: { delay, type: 'spring', duration: 1.5, bounce: 0 },
          opacity: { delay, duration: 0.01 },
        },
      }
    },
  }

  function Submit(props) {
    api.defaults.headers.common[`Authorization`] = 'Bearer ' + localStorage.getItem('token')
    api
      .post(
        '/check',
        {
          system: props,
        },
        header,
      )
      .then((json) => setResult(json.data))
      .catch((error) => {
        alert('시스템 호출 에러')
      })
  }

  const renderTable = () => {
    return result.map((row, index) => {
      return (
        <CTableRow key={row.system}>
          <CTableDataCell scope="row">{index + 1}</CTableDataCell>
          <CTableDataCell>{row.system}</CTableDataCell>
          <CTableDataCell>{row.meantime}</CTableDataCell>
          <CTableDataCell>{row.status}</CTableDataCell>
          <CTableDataCell>
            {row.status === '200 OK' ? (
              <motion.svg
                width="30"
                height="30"
                viewBox="0 0 100 100"
                initial="hidden"
                animate="visible"
              >
                <motion.circle cx="50" cy="50" r="40" stroke="#0099ff" variants={draw} custom={1} />
              </motion.svg>
            ) : (
              <motion.svg
                width="30"
                height="30"
                viewBox="0 0 100 100"
                initial="hidden"
                animate="visible"
              >
                <motion.line
                  x1="15"
                  y1="20"
                  x2="85"
                  y2="85"
                  stroke="#ff0055"
                  variants={draw}
                  custom={2}
                />
                <motion.line
                  x1="15"
                  y1="85"
                  x2="85"
                  y2="20"
                  stroke="#ff0055"
                  variants={draw}
                  custom={2.5}
                />
              </motion.svg>
            )}
          </CTableDataCell>
        </CTableRow>
      )
    })
  }

  return (
    <CRow>
      <CCard className="mb-4">
        <CCardHeader>
          <strong>미들웨어 점검</strong>
        </CCardHeader>
        <CCardBody>
          <CForm className="row gx-3 gy-2 align-items-center">
            <CCol xs="auto">
              <CFormSelect id="autoSizingSelect" onChange={handleChange}>
                <option value="select" selected>
                  Select System
                </option>
                <option value="test">Test</option>
                <option value="prod">Prod</option>
              </CFormSelect>
            </CCol>
            <CCol xs="auto">
              <CButton color="dark" onClick={handleSubmit}>
                submit
              </CButton>
            </CCol>
          </CForm>
        </CCardBody>
        <CCardBody>
          <CTable>
            <CTableHead>
              <CTableRow>
                <CTableHeaderCell scope="col">No.</CTableHeaderCell>
                <CTableHeaderCell scope="col">System</CTableHeaderCell>
                <CTableHeaderCell scope="col">Latency</CTableHeaderCell>
                <CTableHeaderCell scope="col">Status</CTableHeaderCell>
                <CTableHeaderCell scope="col"></CTableHeaderCell>
              </CTableRow>
            </CTableHead>
            <CTableBody> {renderTable()}</CTableBody>
          </CTable>
        </CCardBody>
      </CCard>
    </CRow>
  )
}
export default Syscheck
