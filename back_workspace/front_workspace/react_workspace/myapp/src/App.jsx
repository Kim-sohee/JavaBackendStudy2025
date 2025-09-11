import { BrowserRouter, Link, Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import Company from './pages/Company';
import Portfolio from './pages/Portfolio';
import Support from './pages/Support';

function App() {
  return (
    <BrowserRouter>
      <nav style={{ margin: '30px' }}>
        <Link to="/">Home</Link> |{' '}
        <Link to="/Company">Company</Link> |{' '}
        <Link to="/Portfolio">Portfolio</Link> |{' '}
        <Link to="/Support">Support</Link>
      </nav>

      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/Company" element={<Company />} />
        <Route path="/Portfolio" element={<Portfolio />} />
        <Route path="/Support" element={<Support />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
