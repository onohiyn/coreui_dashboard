import React from 'react'
import { CFooter } from '@coreui/react'

const AppFooter = () => {
  return (
    <CFooter>
      <div>
        <a href="https://coreui.io" target="_blank" rel="noopener noreferrer">
          CoreUI
        </a>
        <span className="ms-1">&copy; .</span>
      </div>
      <div className="ms-auto">
        <span className="me-1">검색은 역시</span>
        <a href="https://www.google.com" target="_blank" rel="noopener noreferrer">
          Google
        </a>
      </div>
    </CFooter>
  )
}

export default React.memo(AppFooter)
