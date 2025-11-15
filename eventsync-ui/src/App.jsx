import EventCardList from "./components/EventCardList";
import Header from "./components/Header";
import { AppBox, AppRoutesBox } from "./styles/StyledComponents";

function App() {
  return (
    <AppBox>
      <Header />
      <AppRoutesBox>
        <EventCardList></EventCardList>
      </AppRoutesBox>
    </AppBox>
  );
}

export default App;
