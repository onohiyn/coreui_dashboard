import React, { useState } from 'react'
import { CFooter } from '@coreui/react'

const AppFooter = () => {
  return (
    <CFooter>
      <div className="ms-auto">
        <span className="me-1">서비스플랫폼팀</span>
        <a href="https://tpops.shinhansec.com" target="_blank" rel="noopener noreferrer">
          Dashboard
        </a>
      </div>
    </CFooter>
  )
}

export default React.memo(AppFooter)
