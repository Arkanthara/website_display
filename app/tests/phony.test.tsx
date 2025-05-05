import {
  render,
  screen,
  waitFor,
  fireEvent,
  cleanup,
} from "@testing-library/react";
import "@testing-library/jest-dom/vitest";
import Phony from "../routes/phony";
import { describe, expect, test, beforeAll, afterEach, afterAll } from "vitest";
import { server } from './server';
import { http, HttpResponse } from 'msw';


describe.sequential("Phony component", () => {
  test("renders loading state and then data", async () => {
    render(<Phony />);
    expect(screen.getByText("Loading Page...")).toBeInTheDocument();
    await waitFor(() => { expect(screen.queryByText("Loading Page...")).not.toBeInTheDocument();});
    await waitFor(() => expect(screen.getByText("ABC123")).toBeInTheDocument());
  });

  test("shows error message when fetch fails", async () => {
    server.use(http.get('http://localhost:8080/api/phony', () => HttpResponse.json({ message: 'Internal Server Error' }, { status: 500 })));
    render(<Phony />);
    await waitFor(() => { expect(screen.queryByText("Loading Page...")).not.toBeInTheDocument();});
    await waitFor(() => {
      expect(screen.getByRole("alert")).toHaveTextContent(
        "Failed to fetch data"
      );
    });
  });

  test("handles column checkbox interactions", async () => {
    render(<Phony />);
    await waitFor(() => { expect(screen.queryByText("Loading Page...")).not.toBeInTheDocument();});
    const columnCheckbox = screen
      .getAllByRole("checkbox")
      .filter((input) => input.getAttribute("name") === "Columns");
    fireEvent.click(columnCheckbox[0]);
    expect(columnCheckbox[0]).not.toBeChecked();
  });

  test("handles chart checkbox interactions", async () => {
    render(<Phony />);
    await waitFor(() => { expect(screen.queryByText("Loading Page...")).not.toBeInTheDocument();});
    const chartCheckbox = screen
      .getAllByRole("checkbox", { hidden: true })
      .filter((input) => input.getAttribute("name") === "Chart Columns");
    if (chartCheckbox[0]) {
      fireEvent.click(chartCheckbox[0]);
      expect(chartCheckbox[0]).toBeChecked();
    }
  });

  test("handles button interactions", async () => {
    render(<Phony />);
    await waitFor(() => { expect(screen.queryByText("Loading Page...")).not.toBeInTheDocument();});
    const buttons = screen.getAllByRole("button");
    expect(buttons.length).toBeGreaterThan(0);
    fireEvent.click(buttons[0]);
  });

  test("sorts data when table headers are clicked", async () => {
    render(<Phony />);
    await waitFor(() => { expect(screen.queryByText("Loading Page...")).not.toBeInTheDocument();});
    const headers = screen.getAllByRole("columnheader");
    const columnHeader = headers.find((header) =>
      header.textContent?.includes("Serial Number")
    );
    expect(columnHeader).toBeTruthy();

    if (columnHeader) {
      fireEvent.click(columnHeader);
      await waitFor(() => { expect(screen.queryByText("Loading Page...")).not.toBeInTheDocument();});

      await waitFor(() => {
        const rows = screen
          .getAllByRole("row")
          .filter((row) => row.getAttribute("aria-rowindex") !== "1"); // skip header

        const columnIndex = 0; // for example: 0 = Time, 1 = Serial Number, etc.

        const values = rows.map((row) => {
          const columns = row.textContent?.trim().split(/\s+/) || [];
          return columns[columnIndex] || "";
        });
        const sorted = [...values].sort((a, b) => (a < b ? -1 : 1));
        expect(values).toEqual(sorted);
      });


      fireEvent.click(columnHeader);
      await waitFor(() => { expect(screen.queryByText("Loading Page...")).not.toBeInTheDocument();});

      await waitFor(() => {
        const rows = screen
          .getAllByRole("row")
          .filter((row) => row.getAttribute("aria-rowindex") !== "1"); // skip header

        const columnIndex = 0; // for example: 0 = Time, 1 = Serial Number, etc.

        const values = rows.map((row) => {
          const columns = row.textContent?.trim().split(/\s+/) || [];
          return columns[columnIndex] || "";
        });
        const sorted = [...values].sort((a, b) => (a < b ? 1 : -1));
        expect(values).toEqual(sorted);
      });
    }
  });

  test("handles datetime picker interaction", async () => {
    render(<Phony />);
    await waitFor(() => { expect(screen.queryByText("Loading Page...")).not.toBeInTheDocument();});
    const dateInputs = screen.getAllByPlaceholderText(
      "dd/MM/yyyy ~ dd/MM/yyyy"
    );
    expect(dateInputs.length).toBeGreaterThanOrEqual(1);
    fireEvent.change(dateInputs[0], {
      target: { value: "01/01/2023 ~ 07/01/2023" },
    });

    const button = screen
      .getAllByRole("button")
      .find((btn) => btn.textContent?.toLowerCase() === "search by time range");
    expect(button).toBeTruthy();

    if (button) {
      fireEvent.click(button);
      await waitFor(() => { expect(screen.queryByText("Loading Page...")).not.toBeInTheDocument();});
      await waitFor(() =>
        expect(screen.getByText("Gnyahaha")).toBeInTheDocument()
      );
      server.use(http.get('http://localhost:8080/api/phony/findByTimeBetween', () => HttpResponse.json({ message: 'Internal Server Error' }, { status: 500 })));
      fireEvent.click(button);
      await waitFor(() =>
        expect(screen.getByRole("alert")).toHaveTextContent(
          "Error: Failed to fetch by time range: 500"
        )
      );
    }
  });

  test("handles time picker interaction", async () => {
    render(<Phony />);
    await waitFor(() => { expect(screen.queryByText("Loading Page...")).not.toBeInTheDocument();});
    const timeInputs = screen.getAllByPlaceholderText("HH:mm:ss ~ HH:mm:ss");
    expect(timeInputs.length).toBeGreaterThanOrEqual(1);
    fireEvent.change(timeInputs[0], {
      target: { value: "08:00:00 ~ 18:00:00" },
    });

    const button = screen
      .getAllByRole("button")
      .find((btn) => btn.textContent?.toLowerCase() === "search by time range");
    expect(button).toBeTruthy();

    if (button) {
      fireEvent.click(button);
      await waitFor(() => { expect(screen.queryByText("Loading Page...")).not.toBeInTheDocument();});
      await waitFor(() =>
        expect(screen.getByText("Gnyahaha")).toBeInTheDocument()
      );
      server.use(http.get('http://localhost:8080/api/phony/findByTimeBetween', () => HttpResponse.json({ message: 'Internal Server Error' }, { status: 500 })));
      fireEvent.click(button);
      await waitFor(() =>
        expect(screen.getByRole("alert")).toHaveTextContent(
          "Error: Failed to fetch by time range: 500"
        )
      );
    }

  });

  test("filters by serial number", async () => {
    render(<Phony />);
    await waitFor(() => { expect(screen.queryByText("Loading Page...")).not.toBeInTheDocument();});
    await waitFor(() => screen.getByText("ABC123"));
    const serialInput = screen.getByPlaceholderText(/serial number/i);
    fireEvent.change(serialInput, { target: { value: "XYZ789" } });
    await waitFor(() => expect(screen.getByText("XYZ789")).toBeInTheDocument());
    const button = screen
      .getAllByRole("button")
      .find(
        (btn) => btn.textContent?.toLowerCase() === "search by serial number"
      );
    expect(button).toBeTruthy();

    if (button) {
      fireEvent.click(button);
      await waitFor(() => { expect(screen.queryByText("Loading Page...")).not.toBeInTheDocument();});
      await waitFor(() =>
        expect(screen.getByText("Gnyahaha")).toBeInTheDocument()
      );
      server.use(http.get('http://localhost:8080/api/phony/findBySerialNumber', () => HttpResponse.json({ message: 'Internal Server Error' }, { status: 500 })));
      fireEvent.click(button);
      await waitFor(() =>
        expect(screen.getByRole("alert")).toHaveTextContent(
          "Error: Failed to fetch by serial number: 500"
        )
      );
    }
  });
  server.close();
});
