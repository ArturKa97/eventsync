import styled from "@emotion/styled";
import {
  AppBar,
  Box,
  Typography,
  Container,
  Card,
  CardContent,
  CardActionArea,
} from "@mui/material";

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
  flexWrap: "wrap",
  maxWidth: "1200",
  padding: "4.8rem 0 9.6rem 0",
  margin: "0 auto",
});

export const StyledCard = styled(Card)({
  width: 270,
  height: 200,
  margin: "0.5rem 0.5rem",
});

export const StyledCardActionArea = styled(CardActionArea)({
  height: "100%",
});

export const StyledCardContent = styled(CardContent)({
  display: "flex",
  flexDirection: "column",
  height: "100%",
});

export const StyledTitleTypography = styled(Typography)({
  flex: 1,
  display: "flex",
  alignItems: "center",
  justifyContent: "center",
  whiteSpace: "normal",
  wordBreak: "break-word",
});
export const StyledDescriptionTypography = styled(Typography)({
  flex: 1,
  display: "flex",
  whiteSpace: "normal",
  wordBreak: "break-word",
});

export const FormBox = styled(Box)({
  display: "flex",
  justifyContent: "space-between",
  alignItems: "center",
});

export const FormTextFieldBox = styled(Box)({
  display: "flex",
  gap: "2.4rem",
  alignItems: "center",
});

export const FormActionButtonBox = styled(Box)({
  display: "flex",
  alignItems: "center",
  justifyContent: "center",
});
