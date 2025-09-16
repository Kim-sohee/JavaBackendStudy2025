import Preloader from  './components/Preloader'
import Navbar from './components/Navbar'
import Sidebar from './components/Sidebar'
import Footer from './components/Footer'
import { BrowserRouter, Route, Routes } from 'react-router-dom'

import ProductList from './pages/product/ProductList'
import ProductForm from './pages/product/ProductForm'

import OrderList from './pages/order/OrderList'

function App() {

  return (
    <div className="wrapper">
      <BrowserRouter>
        <Preloader/>
        <Navbar/>
        <Sidebar/>
        
        {/* 페이지 전환할 영역 */}
        <div className='content-wrapper'>
          <session className="content">
            <Routes>
              {/* 상품관련 */}
              <Route path="product/list" element={<ProductList/>}/>
              <Route path="product/registform" element={<ProductForm/>}/>

              {/* 주문관련 */}
              <Route path="order/list" element={<OrderList/>}/>

            </Routes>
          </session>
        </div>
        <Footer/>
      </BrowserRouter>
    </div>
  )
}

export default App
