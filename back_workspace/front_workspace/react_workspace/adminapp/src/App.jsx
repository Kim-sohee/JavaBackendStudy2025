import Preloader from  './components/Preloader'
import Navbar from './components/Navbar'
import Sidebar from './components/Sidebar'
import Footer from './components/Footer'

function App() {

  return (
    <div className="wrapper">
      <Preloader/>
      <Navbar/>
      <Sidebar/>
      <Footer/>
    </div>
  )
}

export default App
