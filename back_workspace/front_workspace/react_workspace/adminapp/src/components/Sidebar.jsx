import {Link} from 'react-router-dom'
import React, {useState} from 'react'

export default function Sidebar(){
  const [isOpen, setIsOpen] = useState();

  const toggleMenu = (e)=>{
    setIsOpen(!isOpen);
  };

  return (
  <aside className="main-sidebar sidebar-dark-primary elevation-4">
    {/* <!-- Brand Logo --> */}
    <a href="index3.html" className="brand-link">
      <img src="dist/img/AdminLTELogo.png" alt="AdminLTE Logo" className="brand-image img-circle elevation-3" style={{opacity:".8"}}/>
      <span className="brand-text font-weight-light">AdminLTE 3</span>
    </a>

    {/* <!-- Sidebar --> */}
    <div className="sidebar">
      {/* <!-- Sidebar user panel (optional) --> */}
      <div className="user-panel mt-3 pb-3 mb-3 d-flex">
        <div className="image">
          <img src="dist/img/user2-160x160.jpg" className="img-circle elevation-2" alt="User Image"/>
        </div>
        <div className="info">
          <a href="#" className="d-block">Alexander Pierce</a>
        </div>
      </div>

      {/* <!-- SidebarSearch Form --> */}
      <div className="form-inline">
        <div className="input-group" data-widget="sidebar-search">
          <input className="form-control form-control-sidebar" type="search" placeholder="Search" aria-label="Search"/>
          <div className="input-group-append">
            <button className="btn btn-sidebar">
              <i className="fas fa-search fa-fw"></i>
            </button>
          </div>
        </div>
      </div>

      {/* <!-- Sidebar Menu --> */}
      <nav className="mt-2">
        <ul className="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
          {/* <!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library --> */}
          {/* 상품관련 */}
          <li className={`nav-item ${isOpen ? 'menu-open' : ''}`}>
            <Link to="#" className="nav-link active" onClick={toggleMenu}>
              <i className="nav-icon fas fa-tachometer-alt"></i>
              <p>
                상품관리
                <i className="right fas fa-angle-left"></i>
              </p>
            </Link>
            <ul className="nav nav-treeview">
              <li className="nav-item">
                <Link to="/product/list" className="nav-link active">
                  <i className="far fa-circle nav-icon"></i>
                  <p>상품목록</p>
                </Link>
              </li>
              <li className="nav-item">
                <Link to="/product/registform" className="nav-link">
                  <i className="far fa-circle nav-icon"></i>
                  <p>상품등록</p>
                </Link>
              </li>
            </ul>
          </li>
          
          {/* 주문관련 */}
          <li className={`nav-item ${isOpen ? 'menu-open' : ''}`}>
            <Link to="#" className="nav-link" onClick={toggleMenu}>
              <i className="nav-icon fas fa-tachometer-alt"></i>
              <p>
                주문관리
                <i className="right fas fa-angle-left"></i>
              </p>
            </Link>
            <ul className="nav nav-treeview">
              <li className="nav-item">
                <Link to="/order/list" className="nav-link">
                  <i className="far fa-circle nav-icon"></i>
                  <p>주문목록</p>
                </Link>
              </li>
              <li className="nav-item">
                <Link to="/order/registform" className="nav-link">
                  <i className="far fa-circle nav-icon"></i>
                  <p>주문등록</p>
                </Link>
              </li>
            </ul>
          </li>

          {/* 회원관련 */}
          <li className={`nav-item ${isOpen ? 'menu-open' : ''}`}>
            <Link to="#" className="nav-link" onClick={toggleMenu}>
              <i className="nav-icon fas fa-tachometer-alt"></i>
              <p>
                회원관리
                <i className="right fas fa-angle-left"></i>
              </p>
            </Link>
            <ul className="nav nav-treeview">
              <li className="nav-item">
                <Link to="/product/list" className="nav-link">
                  <i className="far fa-circle nav-icon"></i>
                  <p>회원목록</p>
                </Link>
              </li>
              <li className="nav-item">
                <Link to="/product/registform" className="nav-link">
                  <i className="far fa-circle nav-icon"></i>
                  <p>회원등록</p>
                </Link>
              </li>
            </ul>
          </li>
        </ul>
      </nav>
      {/* <!-- /.sidebar-menu --> */}
    </div>
    {/* <!-- /.sidebar --> */}
  </aside>

    )
}