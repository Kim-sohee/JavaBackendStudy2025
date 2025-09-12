import {BrowserRouter, Routes, Route} from 'react-router-dom'
import BoardList from './pages/BoardList'
import BoardForm from './pages/BoardForm'
import BoardDetail from './pages/BoardDetail'

function App() {

  return (
    <>
      <div>
        게시판
      </div>
      <BrowserRouter>
        <Routes>
          <Route path='/' element={<BoardList/>}/>
          <Route path='/new' element={<BoardForm/>}/>
          <Route path='/view/:boardId' element={<BoardDetail/>}/>
          <Route path='/edit/:boardId' element={<BoardForm/>}/>
        </Routes>
      </BrowserRouter>
    </>
  )
}

export default App
