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
