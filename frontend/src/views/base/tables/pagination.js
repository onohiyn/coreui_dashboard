import React from 'react'
import { CPagination, CPaginationItem } from '@coreui/react'

import PropTypes from 'prop-types'

const Pagination = ({ resultIndex, limit, page, setPage }) => {
  const numPages = Math.ceil(resultIndex / limit)
  return (
    <CPagination className="justify-content-center" aria-label="Page navigation example">
      <CPaginationItem aria-label="Previous" onClick={() => setPage(1)}>
        <span aria-hidden="true">&laquo; </span>
      </CPaginationItem>

      {page > 1 ? (
        <CPaginationItem onClick={() => setPage(page - 1)}> 이전 </CPaginationItem>
      ) : (
        <CPaginationItem disabled> 이전 </CPaginationItem>
      )}

      {page > 1 ? (
        <CPaginationItem onClick={() => setPage(page - 1)}> {page - 1}</CPaginationItem>
      ) : null}

      <CPaginationItem active> {page} </CPaginationItem>

      {resultIndex <= page * limit ? null : (
        <CPaginationItem onClick={() => setPage(page + 1)}> {page + 1}</CPaginationItem>
      )}
      {resultIndex <= page * limit ? (
        <CPaginationItem disabled> 다음 </CPaginationItem>
      ) : (
        <CPaginationItem onClick={() => setPage(page + 1)}> 다음 </CPaginationItem>
      )}
      <CPaginationItem aria-label="Next" onClick={() => setPage(numPages)}>
        <span aria-hidden="true">&raquo;</span>
      </CPaginationItem>
    </CPagination>
  )
}

Pagination.propTypes = {
  resultIndex: PropTypes.number.isRequired,
  limit: PropTypes.number.isRequired,
  page: PropTypes.number.isRequired,
  setPage: PropTypes.func.isRequired,
}

export default Pagination
