import * as yup from "yup";

export const eventFormSchema = yup.object().shape({
  title: yup
    .string()
    .min(1, ({ min }) => `Requires atleast ${min} characters`)
    .max(100, ({ max }) => `Maximum ${max} characters allowed`)
    .required("Required field"),
  description: yup
    .string()
    .min(1, ({ min }) => `Requires atleast ${min} characters`)
    .max(9999, ({ max }) => `Maximum ${max} characters allowed`)
    .required("Required field"),
});

export const eventFeedbackFormSchema = yup.object().shape({
  feedback: yup
    .string()
    .min(1, ({ min }) => `Requires atleast ${min} characters`)
    .max(500, ({ max }) => `Maximum ${max} characters allowed`),
});
