import React from 'react'
import {
  CCard,
  CCardBody,
  CCardHeader,
  CCol,
  CRow,
  CTable,
  CTableBody,
  CTableCaption,
  CTableDataCell,
  CTableHead,
  CTableHeaderCell,
  CTableRow,
  CFormSelect,
  CButton,
  CForm,
  FormEvent,
} from '@coreui/react'
import { Component } from 'react'

class Table extends Component {
  render() {
    return (
      <div>
        <CTableRow>
          <CTableHeaderCell scope="row">1</CTableHeaderCell>
          <CTableDataCell></CTableDataCell>
          <CTableDataCell></CTableDataCell>
          <CTableDataCell>@mdo</CTableDataCell>
        </CTableRow>
        ))
      </div>
    )
  }
}
export default Table
