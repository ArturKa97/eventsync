import { useNavigate } from "react-router-dom";
import {
  HeaderLogoBox,
  LogoTypography,
  StyledHeader,
} from "../styles/StyledComponents";

function Header() {
  const navigate = useNavigate();
  return (
    <StyledHeader>
      <HeaderLogoBox>
        <LogoTypography
          style={{ cursor: "pointer" }}
          onClick={() => navigate("/")}
        >
          EventSync
        </LogoTypography>
      </HeaderLogoBox>
    </StyledHeader>
  );
}
export default Header;
