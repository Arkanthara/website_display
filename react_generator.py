import os
import numpy as np
from pathlib import Path
from typing import List, Tuple
import sys

sys.path.append("/home/fitzwilliam/ES-1023/script/app")

# === CONFIGURATION ===
API_BASE = "http://localhost:8080/api"
REACT_TYPE_MAP = {
    "int": "number",
    "float": "number",
    "bool": "boolean",
    "str": "string",
    "datetime64[ns]": "string",
    "LocalDateTime": "string",
    "String": "string"
}

def write_to_file(path, content):
    os.makedirs(os.path.dirname(path), exist_ok=True)
    with open(path, 'w') as f:
        f.write(content)

def generate_typescript_interface(class_name: str, fields: List[Tuple[str, str, str]]) -> str:
    interface_name = f"Data{class_name}"
    fields_ts = "\n  ".join([
        f"{name}: {type_};" for name, type_, _ in fields
    ])
    return (
f"""\
interface {interface_name} {{
  {fields_ts}
}}
""")

def generate_component(class_name: str, fields: List[Tuple[str, str, str]]):
    interface_name = f"Data{class_name}"
    interface_code = generate_typescript_interface(class_name, fields)
    file_name = f"{class_name.lower()}.tsx"
    field_names = ",".join(f"{{ key: \"{f[0]}\", label: \"{f[2]}\", type: \"{f[1]}\" }}" for f in fields)
    field_declarations = [f"{f[0]}" for f in fields]
    return (
f"""\
import {{ useEffect, useState }} from "react";
import {{ Table }} from "rsuite";
import {{ filter }} from "../components/filter";
import * as Utils from "../components/utils";
import {{ subDays }} from "date-fns";
import {{ Chart as ChartJS, CategoryScale, LinearScale, BarElement, Title, Tooltip, Legend }} from "chart.js";

ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend
);

{interface_code}

export default function {class_name}() {{
  const [data, setData] = useState<{interface_name}[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<any>(null);
  const [value, setValue] = useState<[Date, Date]>([subDays(new Date(), 7), new Date()]);
  const [serialNumberFilter, setSerialNumberFilter] = useState<string>("");
  const [checkbox, setCheckbox] = useState<string[]>({field_declarations});
  const [checkboxChart, setCheckboxChart] = useState<string[]>([]);
  const [sortColumn, setSortColumn] = useState<string>("time");
  const [sortType, setSortType] = useState<"asc" | "desc">("asc");
  const field_names = [{field_names}];

  const handleSortColumn = (column: string, type: 'asc' | 'desc' | undefined) => {{
    if (!type) return;
    const key = column as keyof {interface_name};
    setSortColumn(column);
    setSortType(type);
    setLoading(true);
    setTimeout(() => {{
      const sortedData = [...data].sort((a, b) =>
        (a[key] < b[key] ? -1 : 1) * (type === 'asc' ? 1 : -1)
      );
      setData(sortedData);
      setLoading(false);
    }}, 300);
  }};

  useEffect(() => {{
    Utils.fetchData(`{API_BASE}/{class_name.lower()}`, setData, setError, setLoading)
  }}, []);

  return (
    <div style={{{{ padding: "2rem" }}}}>
      <h1>{class_name}</h1>
      {{loading && <div>Loading Page...</div>}}
      {{error && <div role="alert">Error: {{error}}</div>}}

      {{filter(
        `{API_BASE}/{class_name.lower()}`,
        value,
        setValue,
        data,
        setData,
        loading,
        setLoading,
        error,
        setError,
        serialNumberFilter,
        setSerialNumberFilter
      )}}

      {{Utils.checkboxGroup(field_names, "Columns", checkbox, setCheckbox)}}

      <Table
        data={{data}}
        autoHeight
        loading={{loading}}
        sortColumn={{sortColumn}}
        sortType={{sortType}}
        onSortColumn={{handleSortColumn}}
      >
        {{Utils.renderTableColumns(field_names, checkbox)}}
      </Table>

      {{Utils.checkboxGroup(field_names, "Chart Columns", checkboxChart, setCheckboxChart, true)}}

      {{Utils.renderBarCharts(data, checkboxChart)}}
    </div>
  );
}}
""")

def write_utils():
    return (
"""\
import { format } from "date-fns";
import { Checkbox, CheckboxGroup, Table } from "rsuite";
import { Bar } from "react-chartjs-2";
import { generateChartData, generateChartOptions } from "./chartUtils";

export async function fetchData(
  url: string,
  setData: (data: any[]) => void,
  setError: (error: string) => void,
  setLoading: (loading: boolean) => void
) {
  setLoading(true);
  try {
    const response = await fetch(url);
    if (!response.ok) {
      setError(`Failed to fetch data: ${response.status}`);
    } else {
      const json = await response.json();
      const formatted = json.map((item: any) => ({
        ...item,
        time: format(new Date(item.time), "dd-MM HH:mm"),
      }));
      setData(formatted);
    }
  } catch (err) {
    setError(err instanceof Error ? err.message : "Unknown error");
  } finally {
    setLoading(false);
  }
}

export async function fetchByDateRange(
  baseUrl: string,
  value: [Date, Date],
  setData: (data: any[]) => void,
  setError: (error: string) => void,
  setLoading: (loading: boolean) => void
) {
  setLoading(true);
  try {
    const url = new URL(baseUrl + "/findByTimeBetween");
    url.searchParams.append("start", value[0].toISOString().split(".")[0]);
    url.searchParams.append("end", value[1].toISOString().split(".")[0]);
    const response = await fetch(url.toString());
    if (!response.ok) {
      setError(`Failed to fetch by time range: ${response.status}`);
    } else {
      const json = await response.json();
      const formatted = json.map((item: any) => ({
        ...item,
        time: format(new Date(item.time), "dd-MM HH:mm"),
      }));
      setData(formatted);
    }
  } catch (err) {
    setError(err instanceof Error ? err.message : "Unknown error");
  } finally {
    setLoading(false);
  }
}

export async function fetchBySerialNumber(
  baseUrl: string,
  serialNumber: string,
  setData: (data: any[]) => void,
  setError: (error: string) => void,
  setLoading: (loading: boolean) => void
) {
  setLoading(true);
  try {
    const url = new URL(baseUrl + "/findBySerialNumber");
    url.searchParams.append("serialNumber", serialNumber);
    const response = await fetch(url.toString());
    if (!response.ok) {
      setError(`Failed to fetch by serial number: ${response.status}`);
    } else {
      const json = await response.json();
      const formatted = json.map((item: any) => ({
        ...item,
        time: format(new Date(item.time), "dd-MM HH:mm"),
      }));
      setData(formatted);
    }
  } catch (err) {
    setError(err instanceof Error ? err.message : "Unknown error");
  } finally {
    setLoading(false);
  }
}

export function renderTableColumns(fields: { key: string; label: string; type: string; }[], checkbox: string[]) {
  return fields
    .filter(item => checkbox.includes(item.key))
    .map((item) => (
      <Table.Column key={item.key} flexGrow={1} sortable>
        <Table.HeaderCell>{item.label}</Table.HeaderCell>
        <Table.Cell dataKey={item.key} />
      </Table.Column>
    ));
}

export function renderBarCharts(data: any[], keys: string[]) {
  return keys.map((key) => (
    <Bar key={key} data={generateChartData(data, key)} options={generateChartOptions(key)} />
  ));
}

export function checkboxGroup(field_names: { key: string; label: string; type: string; }[], name: string, checkbox: string[], setCheckbox: React.Dispatch<React.SetStateAction<string[]>>, onlyNumber: boolean = false) {
  const fields = onlyNumber ? field_names.filter(name => name.type === "number") : field_names;
  return (
      <CheckboxGroup inline name={name} value={checkbox} onChange={(c) => setCheckbox(Array.isArray(c) ? c.map(String) : [])}>
        {fields.map((name) => <Checkbox key={name.key} value={name.key}>{name.label}</Checkbox>)}
      </CheckboxGroup>);
};
""")

def write_filter():
    return (
"""\
import {
  Button,
  Input,
  DateRangePicker,
  TimeRangePicker,
  HStack,
  VStack,
  Stack,
} from "rsuite";
import React from "react";
import * as Utils from "./utils";


export function filter(
    url: string,
    value: [Date, Date],
    setValue: React.Dispatch<React.SetStateAction<[Date, Date]>>,
    data: any[],
    setData: (data: any[]) => void,
    loading: boolean,
    setLoading: (loading: boolean) => void,
    error: any,
    setError: (error: any) => void,
    serialNumberFilter: string,
    setSerialNumberFilter: (serialNumberFilter: string) => void,
  ) {
  return (
    <VStack spacing={20}>
      <HStack spacing={20}>
        <Stack.Item grow={1}>
          <DateRangePicker
            value={value}
            onChange={(newvalue) => (newvalue ? setValue(newvalue) : null)}
          />
        </Stack.Item>
        <Stack.Item grow={1}>
          <TimeRangePicker
            value={value}
            onChange={(newvalue) => (newvalue ? setValue(newvalue) : null)}
            format={"HH:mm:ss"}
          />
        </Stack.Item>

        <Stack.Item grow={1}>
          <Button
            onClick={() =>
              Utils.fetchByDateRange(url, value, setData, setError, setLoading)
            }
          >
            Search by Time Range
          </Button>
        </Stack.Item>
      </HStack>
      <HStack spacing={20}>
        <Stack.Item grow={1}>
          <Input
            value={serialNumberFilter}
            onChange={(e) => setSerialNumberFilter(e.replace(/\\+/g, ""))}
            placeholder="Search by Serial Number"
          />
        </Stack.Item>
        <Stack.Item grow={1}>
          <Button
            onClick={() =>
              Utils.fetchBySerialNumber(
                url,
                serialNumberFilter,
                setData,
                setError,
                setLoading
              )
            }
          >
            Search by Serial Number
          </Button>
        </Stack.Item>
      </HStack>{" "}
      <Button
        appearance="primary"
        onClick={() => Utils.fetchData(url, setData, setError, setLoading)}
      >
        Reset
      </Button>
    </VStack>
  );
}
""")

def write_chart():
    return (
"""\
import { generateSmoothColors } from "./colorUtils";
export const generateChartData = (data: any[], key: string) => {
  return {
    labels: [key],
    datasets: data.map((item, i) => {
      const { backgroundColor, borderColor } = generateSmoothColors(
        data.length,
        i
      );
      return {
        label: item.serialNumber + " " + item.time,
        data: [item[key]],
        backgroundColor: backgroundColor,
        borderColor: borderColor,
        borderWidth: 2,
      };
    }),
  };
};

export const generateChartOptions = (key: string) => {
  return {
    responsive: true,
    plugins: {
      legend: {
        position: "right" as const, // Ensure that TypeScript understands this value as a literal type 'right' and not just as a generic string.
        labels: {
          color: "#cad3f5",
          font: {
            size: 14,
            family: "Arial",
          },
          padding: 40,
        },
      },
      title: {
        display: true,
        text: key,
        font: {
          size: 32,
          family: "Arial",
          weight: "bold" as const,
        },
        color: "#cad3f5",
      },
    },
    scales: {
      x: {
        ticks: { color: "#cad3f5" },
        grid: { color: "#cad3f5" },
      },
      y: {
        ticks: { color: "#cad3f5" },
        grid: { color: "#5b6078" },
      },
    },
  };
};
""")

def write_color():
    return (
"""\
export const hsvToRgb = (h: number, s: number, v: number, a: number) => {
  let r: number, g: number, b: number;
  s /= 100;
  v /= 100;
  const c = v * s;
  const x = c * (1 - Math.abs(((h / 60) % 2) - 1));
  const m = v - c;

  if (h >= 0 && h < 60) {
    r = c;
    g = x;
    b = 0;
  } else if (h >= 60 && h < 120) {
    r = x;
    g = c;
    b = 0;
  } else if (h >= 120 && h < 180) {
    r = 0;
    g = c;
    b = x;
  } else if (h >= 180 && h < 240) {
    r = 0;
    g = x;
    b = c;
  } else if (h >= 240 && h < 300) {
    r = x;
    g = 0;
    b = c;
  } else {
    r = c;
    g = 0;
    b = x;
  }

  r = Math.round((r + m) * 255);
  g = Math.round((g + m) * 255);
  b = Math.round((b + m) * 255);

  return `rgba(${r}, ${g}, ${b}, ${a})`;
};

export const generateSmoothColors = (numBar: number, i: number) => {
  const hue = (360 / numBar) * i;
  const backgroundColor = hsvToRgb(hue, 70, 70, 0.7);
  const borderColor = hsvToRgb(hue, 70, 50, 1);
  return { backgroundColor, borderColor };
};
""")

def write_navigation(models: List[Tuple[str, List[Tuple[str, str, str]]]]):
    links = "\n".join([
        f'        <Nav.Item as={{Link}} to="/{name.lower()}">{name}</Nav.Item>' for name, _ in models
    ])
    return (
f"""\
import {{ Navbar, Nav }} from "rsuite";
import {{ Link }} from "react-router";

export default function Navigation() {{
  return (
    <Navbar>
      <Navbar.Brand href="/">MyApp</Navbar.Brand>
      <Nav>
        <Nav.Item as={{Link}} to="/">Home</Nav.Item>
{links}
      </Nav>
    </Navbar>
  );
}}
""")

def write_layout():
    return (
"""\
import { Outlet, ScrollRestoration, Scripts, Links, Meta } from "react-router";
import Navigation from "./navigation";
import { CustomProvider, Container } from "rsuite";
import "rsuite/dist/rsuite.min.css";

export default function Layout() {
  return (
    <html lang="en">
      <head>
        <title>Gnyahaha</title>
        <meta charSet="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <Meta />
        <Links />
      </head>
      <body>
        <CustomProvider theme="dark">
          <Container>
            <Navigation />
            <Outlet />
            <ScrollRestoration />
            <Scripts />
          </Container>
        </CustomProvider>
      </body>
    </html>
  );
}
""")

def write_home():
    return (
"""\
export default function Home() {
  return (
    <div style={{ paddingInline: "2rem" }}>
      <h1>Home</h1>
      <p>Welcome to the homepage !</p>
      <p>This is a test</p>
    </div>
  );
}
""")

def write_routes(models: List[Tuple[str, List[Tuple[str, str, str]]]]):
    entries = "\n    ".join([
        f'route("/{name.lower()}", "./routes/{name.lower()}.tsx"),' for name, _ in models
    ])
    return (
f"""\
import {{ layout, index, route }} from "@react-router/dev/routes";

export default [
  layout("./components/layout.tsx", [
    index("./routes/home.tsx"),
    {entries}
  ]),
];
""")

def write_root():
    return (
"""\
import { isRouteErrorResponse, useRouteError, Outlet } from "react-router";

import type { Route } from "./+types/root";
// import "./app.css";

export const links: Route.LinksFunction = () => [
  { rel: "preconnect", href: "https://fonts.googleapis.com" },
  {
    rel: "preconnect",
    href: "https://fonts.gstatic.com",
    crossOrigin: "anonymous",
  },
];

export default function App() {
  return <Outlet />;
}

export function ErrorBoundary() {
  let message = "Oops!";
  let details = "An unexpected error occurred.";
  let stack: string | undefined;

  const error = useRouteError();

  if (isRouteErrorResponse(error)) {
    message = error.status === 404 ? "404" : "Error";
    details =
      error.status === 404
        ? "The requested page could not be found."
        : error.statusText || details;
  } else if (import.meta.env.DEV && error && error instanceof Error) {
    details = error.message;
    stack = error.stack;
  }

  return (
    <main className="pt-16 p-4 container mx-auto">
      <h1>{message}</h1>
      <p>{details}</p>
      {stack && (
        <pre className="w-full p-4 overflow-x-auto">
          <code>{stack}</code>
        </pre>
      )}
    </main>
  );
}
""")

def write_package():
    return (
"""\
{
  "name": "frontend",
  "private": true,
  "type": "module",
  "scripts": {
    "build": "react-router build",
    "dev": "react-router dev",
    "test": "vitest",
    "coverage": "vitest run --coverage",
    "start": "react-router-serve ./build/server/index.js",
    "typecheck": "react-router typegen && tsc"
  },
  "dependencies": {
    "@catppuccin/tailwindcss": "^0.1.6",
    "@react-router/node": "^7.5.2",
    "@react-router/serve": "^7.5.2",
    "chart.js": "latest",
    "date-fns": "^4.1.0",
    "isbot": "^5",
    "react": "latest",
    "react-chartjs-2": "latest",
    "react-dom": "latest",
    "react-router": "latest",
    "rsuite": "^5.79.1"
  },
  "devDependencies": {
    "@react-router/dev": "^7.5.2",
    "@tailwindcss/vite": "latest",
    "@testing-library/jest-dom": "latest",
    "@testing-library/react": "latest",
    "@types/node": "latest",
    "@types/react": "latest",
    "@types/react-dom": "latest",
    "@vitest/coverage-v8": "latest",
    "@vitest/ui": "^3.1.1",
    "depcheck": "^1.4.7",
    "jsdom": "latest",
    "msw": "^2.7.5",
    "tailwindcss": "latest",
    "typescript": "latest",
    "vite": "^6.2.6",
    "vite-tsconfig-paths": "latest",
    "vitest": "latest"
  }
}
""")

def write_vitest():
    return (
"""\
import { defineConfig } from "vitest/config";

export default defineConfig({
  test: {
    environment: "jsdom",
    // fileParallelism: false,
    testTimeout: 10000,
    isolate: true,
    globals: true,
    outputFile: "./result.html",
    // globalSetup: ["./app/tests/globalSetup.ts"],
    setupFiles: ["./app/tests/setup.ts"],
    coverage: {
      reporter: ["text", "lcov"],
      exclude: [
        "**/*.config.ts",
        "**/.react-router/**",
        "**/build/**",
        "**/html/**",
        "**/tests/**",
      ],
    },
  },
});
""")

def write_vite():
    return (
"""\
import { reactRouter } from "@react-router/dev/vite";
import tailwindcss from "@tailwindcss/vite";
import { defineConfig } from "vite";
import tsconfigPaths from "vite-tsconfig-paths";

export default defineConfig({
  plugins: [tailwindcss(), reactRouter(), tsconfigPaths()],
});
""")

def write_tsconfig():
    return (
"""\
{
  "include": [
    "**/*",
    "**/.server/**/*",
    "**/.client/**/*",
    ".react-router/types/**/*"
  ],
  "compilerOptions": {
    "lib": ["DOM", "DOM.Iterable", "ES2022"],
    "types": ["node", "vite/client"],
    "target": "ES2022",
    "module": "ES2022",
    "moduleResolution": "bundler",
    "jsx": "react-jsx",
    "rootDirs": [".", "./.react-router/types"],
    "baseUrl": ".",
    "paths": {
      "~/*": ["./app/*"]
    },
    "esModuleInterop": true,
    "verbatimModuleSyntax": true,
    "noEmit": true,
    "resolveJsonModule": true,
    "skipLibCheck": true,
    "strict": true
  }
}
""")

def write_reactconfig():
    return (
"""\
import type { Config } from "@react-router/dev/config";

export default {
  // Config options...
  // Server-side render by default, to enable SPA mode set this to `false`
  ssr: true,
} satisfies Config;
""")

def write_docker():
    return (
"""\
FROM node:23-alpine AS base
RUN apk add --no-cache shadow
RUN npm install  --ignore-scripts -g npm@latest

FROM base AS development-dependencies-env
COPY ./package.json package-lock.json /app/
WORKDIR /app
RUN npm ci --ignore-scripts

FROM base AS production-dependencies-env
COPY ./package.json package-lock.json /app/
WORKDIR /app
RUN npm ci --ignore-scripts --omit=dev

FROM base AS build-env
COPY package.json package-lock.json tsconfig.json /app/
COPY app/ /app/app/
COPY public/ /app/public/
COPY *.ts /app/
RUN ls /app -R
COPY --from=development-dependencies-env /app/node_modules /app/node_modules
WORKDIR /app
RUN npm run build

FROM base 
RUN groupadd -r appgroup && useradd -r -g appgroup appuser
COPY ./package.json package-lock.json /app/
COPY --from=production-dependencies-env /app/node_modules /app/node_modules
COPY --from=build-env /app/build /app/build
WORKDIR /app
RUN chown -R appuser:appgroup /app
USER appuser
CMD ["npm", "run", "start"]
""")

def convert_python_to_react(input_path, output_dir):
    from dataclass import Data
    data = Data(input_path, ';')
    # data.extractData()
    models = data.extract_class_and_fields({"int": "number", "str": "string", "datetime": "Date"})
    for class_name, fields in models:
        write_to_file(f"{output_dir}/app/routes/{class_name.lower()}.tsx", generate_component(class_name, fields))
    write_to_file(f"{output_dir}/app/routes/home.tsx", write_home())
    write_to_file(f"{output_dir}/app/components/navigation.tsx", write_navigation(models))
    write_to_file(f"{output_dir}/app/components/utils.tsx", write_utils())
    write_to_file(f"{output_dir}/app/components/filter.tsx", write_filter())
    write_to_file(f"{output_dir}/app/components/chartUtils.tsx", write_chart())
    write_to_file(f"{output_dir}/app/components/colorUtils.tsx", write_color())
    write_to_file(f"{output_dir}/app/components/layout.tsx", write_layout())
    write_to_file(f"{output_dir}/app/root.tsx", write_root())
    write_to_file(f"{output_dir}/app/routes.ts", write_routes(models))
    write_to_file(f"{output_dir}/package.json", write_package())
    write_to_file(f"{output_dir}/vitest.config.ts", write_vitest())
    write_to_file(f"{output_dir}/vite.config.ts", write_vite())
    write_to_file(f"{output_dir}/react-router.config.ts", write_reactconfig())
    write_to_file(f"{output_dir}/tsconfig.json", write_tsconfig())
    write_to_file(f"{output_dir}/Dockerfile", write_docker())
    print("✅ All React components and routes generated successfully.")

if __name__ == "__main__":
    convert_python_to_react("/home/fitzwilliam/Documents/data/2025_03_31_10-39-50.MM00493.txt", ".")

