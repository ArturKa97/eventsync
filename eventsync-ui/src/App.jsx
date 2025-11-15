import Header from "./components/Header";
import PageRoutes from "./routes/PageRoutes";
import { BrowserRouter } from "react-router-dom";
import { AppBox, AppRoutesBox } from "./styles/StyledComponents";

function App() {
  return (
    <BrowserRouter>
      <AppBox>
        <Header />
        <AppRoutesBox>
          <PageRoutes />
        </AppRoutesBox>
      </AppBox>
    </BrowserRouter>
  );
}

export default App;
