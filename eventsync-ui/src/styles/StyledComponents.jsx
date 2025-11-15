import styled from "@emotion/styled";
import { AppBar, Box, Typography, Container } from "@mui/material";

export const AppBox = styled(Box)({
  display: "flex",
  flexDirection: "column",
  minHeight: "100vh",
});

export const StyledHeader = styled(AppBar)({
  position: "sticky",
  display: "flex",
  alignItems: "center",
});

export const HeaderLogoBox = styled(Box)({
  display: "flex",
  alignItems: "center",
  gap: "0.4rem",
});

export const LogoTypography = styled(Typography)({
  fontSize: "2.0rem",
  letterSpacing: "0.15rem",
  fontWeight: "600",
});

export const AppRoutesBox = styled(Box)({
  flex: 1,
});

export const MainContainer = styled(Container)({
  display: "flex",
  flexDirection: "row",
  maxWidth: "1200",
  padding: "4.8rem 0 9.6rem 0",
  margin: "0 auto",
});
