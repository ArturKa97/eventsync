import { HeaderLogoBox, LogoTypography, StyledHeader } from "../styles/StyledComponents"

function Header() {
    return (
        <StyledHeader>
            <HeaderLogoBox>
                <LogoTypography>
                    EventSync
                </LogoTypography>
            </HeaderLogoBox>
        </StyledHeader>
    )
}
export default Header