import os
from pathlib import Path
import sys

# Add path for accessing extract_class_and_fields from react_generator.py
sys.path.append("/home/fitzwilliam/ES-1023/script/app")

from react_generator import write_to_file

API_BASE = "http://localhost:8080/api"
# Configuration paths
COMPONENTS_DIR = Path("app/routes")
COMPONENTS_DIR_NAVIGATION = Path("app/components")
TESTS_DIR = Path("app/tests")
DATA_CSV_PATH = "../data/2025_03_31_10-39-50.MM00493.txt"  # Ensure this matches your setup

# Ensure tests directory exists
TESTS_DIR.mkdir(parents=True, exist_ok=True)

# Template for generating Vitest tests with React Testing Library
def generate_pages_test(class_name):
    return (
f"""\
import {{
  render,
  screen,
  waitFor,
  fireEvent,
  cleanup,
}} from "@testing-library/react";
import "@testing-library/jest-dom/vitest";
import {class_name} from "../routes/{class_name.lower()}";
import {{ describe, expect, test, beforeAll, afterEach, afterAll }} from "vitest";
import {{ server }} from './server';
import {{ http, HttpResponse }} from 'msw';


describe.sequential("{class_name} component", () => {{
  test("renders loading state and then data", async () => {{
    render(<{class_name} />);
    expect(screen.getByText("Loading Page...")).toBeInTheDocument();
    await waitFor(() => {{ expect(screen.queryByText("Loading Page...")).not.toBeInTheDocument();}});
    await waitFor(() => expect(screen.getByText("ABC123")).toBeInTheDocument());
  }});

  test("shows error message when fetch fails", async () => {{
    server.use(http.get('{API_BASE}/{class_name.lower()}', () => HttpResponse.json({{ message: 'Internal Server Error' }}, {{ status: 500 }})));
    render(<{class_name} />);
    await waitFor(() => {{ expect(screen.queryByText("Loading Page...")).not.toBeInTheDocument();}});
    await waitFor(() => {{
      expect(screen.getByRole("alert")).toHaveTextContent(
        "Failed to fetch data"
      );
    }});
  }});

  test("handles column checkbox interactions", async () => {{
    render(<{class_name} />);
    await waitFor(() => {{ expect(screen.queryByText("Loading Page...")).not.toBeInTheDocument();}});
    const columnCheckbox = screen
      .getAllByRole("checkbox")
      .filter((input) => input.getAttribute("name") === "Columns");
    fireEvent.click(columnCheckbox[0]);
    expect(columnCheckbox[0]).not.toBeChecked();
  }});

  test("handles chart checkbox interactions", async () => {{
    render(<{class_name} />);
    await waitFor(() => {{ expect(screen.queryByText("Loading Page...")).not.toBeInTheDocument();}});
    const chartCheckbox = screen
      .getAllByRole("checkbox", {{ hidden: true }})
      .filter((input) => input.getAttribute("name") === "Chart Columns");
    if (chartCheckbox[0]) {{
      fireEvent.click(chartCheckbox[0]);
      expect(chartCheckbox[0]).toBeChecked();
    }}
  }});

  test("handles button interactions", async () => {{
    render(<{class_name} />);
    await waitFor(() => {{ expect(screen.queryByText("Loading Page...")).not.toBeInTheDocument();}});
    const buttons = screen.getAllByRole("button");
    expect(buttons.length).toBeGreaterThan(0);
    fireEvent.click(buttons[0]);
  }});

  test("sorts data when table headers are clicked", async () => {{
    render(<{class_name} />);
    await waitFor(() => {{ expect(screen.queryByText("Loading Page...")).not.toBeInTheDocument();}});
    const headers = screen.getAllByRole("columnheader");
    const columnHeader = headers.find((header) =>
      header.textContent?.includes("Serial Number")
    );
    expect(columnHeader).toBeTruthy();

    if (columnHeader) {{
      fireEvent.click(columnHeader);
      await waitFor(() => {{ expect(screen.queryByText("Loading Page...")).not.toBeInTheDocument();}});

      await waitFor(() => {{
        const rows = screen
          .getAllByRole("row")
          .filter((row) => row.getAttribute("aria-rowindex") !== "1"); // skip header

        const columnIndex = 0; // for example: 0 = Time, 1 = Serial Number, etc.

        const values = rows.map((row) => {{
          const columns = row.textContent?.trim().split(/\\s+/) || [];
          return columns[columnIndex] || "";
        }});
        const sorted = [...values].sort((a, b) => (a < b ? -1 : 1));
        expect(values).toEqual(sorted);
      }});


      fireEvent.click(columnHeader);
      await waitFor(() => {{ expect(screen.queryByText("Loading Page...")).not.toBeInTheDocument();}});

      await waitFor(() => {{
        const rows = screen
          .getAllByRole("row")
          .filter((row) => row.getAttribute("aria-rowindex") !== "1"); // skip header

        const columnIndex = 0; // for example: 0 = Time, 1 = Serial Number, etc.

        const values = rows.map((row) => {{
          const columns = row.textContent?.trim().split(/\\s+/) || [];
          return columns[columnIndex] || "";
        }});
        const sorted = [...values].sort((a, b) => (a < b ? 1 : -1));
        expect(values).toEqual(sorted);
      }});
    }}
  }});

  test("handles datetime picker interaction", async () => {{
    render(<{class_name} />);
    await waitFor(() => {{ expect(screen.queryByText("Loading Page...")).not.toBeInTheDocument();}});
    const dateInputs = screen.getAllByPlaceholderText(
      "dd/MM/yyyy ~ dd/MM/yyyy"
    );
    expect(dateInputs.length).toBeGreaterThanOrEqual(1);
    fireEvent.change(dateInputs[0], {{
      target: {{ value: "01/01/2023 ~ 07/01/2023" }},
    }});

    const button = screen
      .getAllByRole("button")
      .find((btn) => btn.textContent?.toLowerCase() === "search by time range");
    expect(button).toBeTruthy();

    if (button) {{
      fireEvent.click(button);
      await waitFor(() => {{ expect(screen.queryByText("Loading Page...")).not.toBeInTheDocument();}});
      await waitFor(() =>
        expect(screen.getByText("Gnyahaha")).toBeInTheDocument()
      );
      server.use(http.get('{API_BASE}/{class_name.lower()}/findByTimeBetween', () => HttpResponse.json({{ message: 'Internal Server Error' }}, {{ status: 500 }})));
      fireEvent.click(button);
      await waitFor(() =>
        expect(screen.getByRole("alert")).toHaveTextContent(
          "Error: Failed to fetch by time range: 500"
        )
      );
    }}
  }});

  test("handles time picker interaction", async () => {{
    render(<{class_name} />);
    await waitFor(() => {{ expect(screen.queryByText("Loading Page...")).not.toBeInTheDocument();}});
    const timeInputs = screen.getAllByPlaceholderText("HH:mm:ss ~ HH:mm:ss");
    expect(timeInputs.length).toBeGreaterThanOrEqual(1);
    fireEvent.change(timeInputs[0], {{
      target: {{ value: "08:00:00 ~ 18:00:00" }},
    }});

    const button = screen
      .getAllByRole("button")
      .find((btn) => btn.textContent?.toLowerCase() === "search by time range");
    expect(button).toBeTruthy();

    if (button) {{
      fireEvent.click(button);
      await waitFor(() => {{ expect(screen.queryByText("Loading Page...")).not.toBeInTheDocument();}});
      await waitFor(() =>
        expect(screen.getByText("Gnyahaha")).toBeInTheDocument()
      );
      server.use(http.get('{API_BASE}/{class_name.lower()}/findByTimeBetween', () => HttpResponse.json({{ message: 'Internal Server Error' }}, {{ status: 500 }})));
      fireEvent.click(button);
      await waitFor(() =>
        expect(screen.getByRole("alert")).toHaveTextContent(
          "Error: Failed to fetch by time range: 500"
        )
      );
    }}

  }});

  test("filters by serial number", async () => {{
    render(<{class_name} />);
    await waitFor(() => {{ expect(screen.queryByText("Loading Page...")).not.toBeInTheDocument();}});
    await waitFor(() => screen.getByText("ABC123"));
    const serialInput = screen.getByPlaceholderText(/serial number/i);
    fireEvent.change(serialInput, {{ target: {{ value: "XYZ789" }} }});
    await waitFor(() => expect(screen.getByText("XYZ789")).toBeInTheDocument());
    const button = screen
      .getAllByRole("button")
      .find(
        (btn) => btn.textContent?.toLowerCase() === "search by serial number"
      );
    expect(button).toBeTruthy();

    if (button) {{
      fireEvent.click(button);
      await waitFor(() => {{ expect(screen.queryByText("Loading Page...")).not.toBeInTheDocument();}});
      await waitFor(() =>
        expect(screen.getByText("Gnyahaha")).toBeInTheDocument()
      );
      server.use(http.get('{API_BASE}/{class_name.lower()}/findBySerialNumber', () => HttpResponse.json({{ message: 'Internal Server Error' }}, {{ status: 500 }})));
      fireEvent.click(button);
      await waitFor(() =>
        expect(screen.getByRole("alert")).toHaveTextContent(
          "Error: Failed to fetch by serial number: 500"
        )
      );
    }}
  }});
  server.close();
}});
""")


def generate_server(models):
    handlers = ""
    for class_name, fields in models:
        handlers += (
f"""\
  http.get('{API_BASE}/{class_name.lower()}', () => HttpResponse.json(mockData)),
  http.get('{API_BASE}/{class_name.lower()}/findByTimeBetween', () => HttpResponse.json(tempMockData)),
  http.get('{API_BASE}/{class_name.lower()}/findBySerialNumber', () => HttpResponse.json(tempMockData)),
""")

    return (
f"""\
import {{ setupServer }} from 'msw/node';
import {{ http, HttpResponse }} from 'msw';

const mockData = [
  {{
    time: new Date().toISOString(),
    serialNumber: "ABC123",
  }},
  {{
    time: new Date().toISOString(),
    serialNumber: "XYZ789",
  }},
  {{
    time: new Date().toISOString(),
    serialNumber: "LMN456",
  }},
];
const tempMockData = [
  {{
    time: new Date().toISOString(),
    serialNumber: "Gnyahaha",
  }}
];

export const handlers = [
{handlers}
];

export const server = setupServer(...handlers);

export const waitForMSW = async () => {{
  for (let i = 0; i < 10; i++) {{
    try {{
      const res = await fetch("{API_BASE}/phony");
      if (res.ok) return true;
    }} catch (_) {{}}
    await new Promise((r) => setTimeout(r, 50));
  }}
  throw new Error("MSW did not initialize in time");
}};
""")

def generate_setup():
    return (
"""\
import { beforeAll, beforeEach, afterEach, afterAll } from 'vitest';
import { server, waitForMSW } from './server';
import { cleanup } from '@testing-library/react';

beforeAll(() => server.listen());
afterEach(() => {
  cleanup();
  server.resetHandlers();
});
afterAll(() => server.close());
""")

def generate_navigation_test(models):
    navigation = ""
    for class_name, fields in models:
        navigation += f"    expect(screen.getByText('{class_name}')).toBeInTheDocument();\n"
    return (
f"""\
import React from 'react';
import {{ describe, expect, test }} from 'vitest';
import {{ render, screen }} from '@testing-library/react';
import {{ MemoryRouter }} from 'react-router';
import Navigation from '../components/navigation';
import '@testing-library/jest-dom/vitest';

describe('<Navigation />', () => {{
  test('renders navigation links correctly', () => {{
   render(
     <MemoryRouter>
       <Navigation />
     </MemoryRouter>
   );
    expect(screen.getByText('Home')).toBeInTheDocument();
{navigation}
  }});
}});
""")

def generate_routes_test():
    return (
"""\
import React from 'react';
import { describe, expect, test } from 'vitest';
import routes from '../routes';

describe('Routes Configuration', () => {
  test('has correct routes defined', () => {
    expect(routes).toBeDefined();
    expect(routes.length).toBeGreaterThan(0);
  });
});
""")

def generate_root_test():
    return (
"""\
import { describe, test, expect, vi } from "vitest";
import { render, screen, cleanup } from "@testing-library/react";
import "@testing-library/jest-dom/vitest";
import {
  createRoutesStub,
  useRouteError,
  isRouteErrorResponse,
} from "react-router";
import App, { ErrorBoundary, links } from "../root";

const Stub = createRoutesStub([
  {
    path: "/",
    Component: App,
  },
]);

describe("Test Root Component", () => {
  render(<Stub initialEntries={["/"]} />);
  test("Outlet component", () => {
    expect(screen.queryByTestId("outlet")).toBeNull();
  });
});

vi.mock(import("react-router"), async (importOriginal) => {
  const actual = await importOriginal();
  return {
    ...actual,
    // your mocked methods
    useRouteError: vi.fn(),
    isRouteErrorResponse: vi.fn(),
  };
});

describe("Test ErrorBoundary", () => {
  test("Test 404 message", () => {
    (useRouteError as vi.Mock).mockReturnValue({
      status: 404,
      statusText: "Not Found",
    });
    (isRouteErrorResponse as vi.Mock).mockReturnValue(true);
    render(<ErrorBoundary />);
    expect(screen.getByText("404")).toBeInTheDocument();
    cleanup();
  });
  test("Test other Route message", () => {
    (useRouteError as vi.Mock).mockReturnValue({
      status: 500,
      statusText: "Internal Server Error",
    });
    (isRouteErrorResponse as vi.Mock).mockReturnValue(true);
    render(<ErrorBoundary />);
    expect(screen.getByText("Internal Server Error")).toBeInTheDocument();
    cleanup();
    (useRouteError as vi.Mock).mockReturnValue({
      status: 500,
    });
    (isRouteErrorResponse as vi.Mock).mockReturnValue(true);
    render(<ErrorBoundary />);
    expect(
      screen.getByText("An unexpected error occurred.")
    ).toBeInTheDocument();
    cleanup();
  });
  test("Test if DevMode", () => {
    vi.stubEnv("DEV", true);
    (useRouteError as vi.Mock).mockReturnValue(
      new Error("You have been hacked !")
    );
    (isRouteErrorResponse as vi.Mock).mockReturnValue(false);
    render(<ErrorBoundary />);
    expect(screen.getByText("You have been hacked !")).toBeInTheDocument();
    cleanup();
  });
  test("Test if not DevMode", () => {
    vi.stubEnv("DEV", false);
    (useRouteError as vi.Mock).mockReturnValue(new Error("Hey Hey Hey !"));
    (isRouteErrorResponse as vi.Mock).mockReturnValue(false);
    render(<ErrorBoundary />);
    expect(
      screen.getByText("An unexpected error occurred.")
    ).toBeInTheDocument();
    cleanup();
  });
  vi.unstubAllEnvs();
  vi.resetModules();
});

describe("links function", () => {
  test("should return the correct links array", () => {
    const result = links();

    // Test if the result is an array
    expect(Array.isArray(result)).toBe(true);

    // Test if the array contains the expected objects
    expect(result).toHaveLength(2); // We expect 2 elements in the array

    // Test the first link object
    expect(result[0]).toEqual({
      rel: "preconnect",
      href: "https://fonts.googleapis.com",
    });

    // Test the second link object
    expect(result[1]).toEqual({
      rel: "preconnect",
      href: "https://fonts.gstatic.com",
      crossOrigin: "anonymous",
    });
  });
});
""")

def generate_layout_test():
    return (
"""\
import Layout from "../components/layout";
import { screen, render } from "@testing-library/react";
import { describe, expect, test } from "vitest";
import "@testing-library/jest-dom/vitest";
import { createRoutesStub } from "react-router";

describe("Test Layout", () => {
  const Stub = createRoutesStub([
    {
      path: "/",
      Component: Layout,
    },
  ]);
  render(<Stub initialEntries={["/"]} />);
  test("Navigation bar is in the document", () => {
    expect(screen.getByRole("navigation")).toBeInTheDocument();
  });
});
""")

def generate_home_test():
    return (
"""\
import { render, screen } from "@testing-library/react";
import Home from "../routes/home";
import "@testing-library/jest-dom/vitest";
import { describe, test, expect } from "vitest";

describe("Test of Homepage", () => {
  test("Render Homepage", () => {
    render(<Home />);

    expect(screen.getByRole("heading", { name: /home/i })).toBeInTheDocument();

    const paragraphs = screen.getAllByText(
      (_, element) => element?.tagName.toLowerCase() === "p"
    );
    expect(paragraphs.length).toBe(2);
  });
});
""")

def generate_chartUtils_test():
    return (
"""\
import { describe, it, expect } from "vitest";
import { generateChartData, generateChartOptions } from "../components/chartUtils";

describe("chartUtils", () => {
    it("should generate chart options", () => {
        const options = generateChartOptions("test");
        expect(options.plugins?.title?.text).toBe("test");
    });
    
    it("should generate chart data", () => {
        const dummyData = [
        { serialNumber: "123", time: new Date().toISOString(), init: 5 },
        { serialNumber: "456", time: new Date().toISOString(), init: 10 },
        ];
        const colorFn = () => ({ backgroundColor: "rgba(0,0,0,0.7)", borderColor: "rgba(0,0,0,1)" });
        const chartData = generateChartData(dummyData, "init", colorFn);
        expect(chartData.datasets.length).toBe(2);
    });
});
""")

def generate_colorUtils_test():
    return (
"""\
import { describe, it, expect } from "vitest";
import { hsvToRgb, generateSmoothColors } from "../components/colorUtils";

describe("hsvToRgb", () => {
  it("converts black (0, 0, 0) to rgba(0, 0, 0, a)", () => {
    expect(hsvToRgb(0, 0, 0, 1)).toBe("rgba(0, 0, 0, 1)");
  });

  it("converts white (0, 0, 100) to rgba(255, 255, 255, a)", () => {
    expect(hsvToRgb(0, 0, 100, 0.5)).toBe("rgba(255, 255, 255, 0.5)");
  });

  it("converts red (0, 100, 100) to rgba(255, 0, 0, a)", () => {
    expect(hsvToRgb(0, 100, 100, 0.8)).toBe("rgba(255, 0, 0, 0.8)");
  });

  it("converts green (120, 100, 100) to rgba(0, 255, 0, a)", () => {
    expect(hsvToRgb(120, 100, 100, 0.3)).toBe("rgba(0, 255, 0, 0.3)");
  });

  it("converts blue (240, 100, 100) to rgba(0, 0, 255, a)", () => {
    expect(hsvToRgb(240, 100, 100, 1)).toBe("rgba(0, 0, 255, 1)");
  });

  it("handles hue wraparound above 360 by default logic", () => {
    const color = hsvToRgb(420, 100, 100, 1); // Should wrap to a reddish color
    expect(color).toMatch(/^rgba\\(\\d+, \\d+, \\d+, 1\\)$/);
  });
});

describe("generateSmoothColors", () => {
  it("generates consistent colors for bar index", () => {
    const result = generateSmoothColors(5, 2);
    expect(result).toHaveProperty("backgroundColor");
    expect(result).toHaveProperty("borderColor");
    expect(result.backgroundColor).toMatch(/^rgba\\(/);
    expect(result.borderColor).toMatch(/^rgba\\(/);
  });

  it("generates distinct colors for different indices", () => {
    const color1 = generateSmoothColors(10, 1);
    const color2 = generateSmoothColors(10, 2);
    expect(color1.backgroundColor).not.toBe(color2.backgroundColor);
    expect(color1.borderColor).not.toBe(color2.borderColor);
  });
});
""")

def generate_tests(input_path, output_dir):
    from dataclass import Data
    data = Data(input_path, ';')
    # data.extractData()
    models = data.extract_class_and_fields({"int": "number", "str": "string", "datetime": "Date"})
    for class_name, fields in models:
        write_to_file(f"{output_dir}/app/tests/{class_name.lower()}.test.tsx", generate_pages_test(class_name))
    write_to_file(f"{output_dir}/app/tests/navigation.test.tsx", generate_navigation_test(models))
    write_to_file(f"{output_dir}/app/tests/routes.test.tsx", generate_routes_test())
    write_to_file(f"{output_dir}/app/tests/server.ts", generate_server(models))
    write_to_file(f"{output_dir}/app/tests/setup.ts", generate_setup())
    write_to_file(f"{output_dir}/app/tests/home.test.tsx", generate_home_test())
    write_to_file(f"{output_dir}/app/tests/layout.test.tsx", generate_layout_test())
    write_to_file(f"{output_dir}/app/tests/root.test.tsx", generate_root_test())
    write_to_file(f"{output_dir}/app/tests/chartUtils.test.tsx", generate_chartUtils_test())
    write_to_file(f"{output_dir}/app/tests/colorUtils.test.tsx", generate_colorUtils_test())

if __name__ == "__main__":
    generate_tests("/home/fitzwilliam/Documents/data/2025_03_31_10-39-50.MM00493.txt", ".")

