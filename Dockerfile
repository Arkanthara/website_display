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
